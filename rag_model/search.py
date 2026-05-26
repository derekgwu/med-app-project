import os
os.environ["HF_HUB_OFFLINE"] = "1"
import json
import numpy as np
import faiss
import requests
from sentence_transformers import SentenceTransformer

OLLAMA_MODEL = "llama3.2"
OLLAMA_URL = "http://localhost:11434/api/generate"

model = SentenceTransformer("./models/all-MiniLM-L6-v2")
index = faiss.read_index("medical.index")

with open("chunk_metadata.json", "r", encoding="utf-8") as f:
    chunks = json.load(f)


def retrieve(query: str, k: int = 5) -> list[dict]:
    q_emb = model.encode([query]).astype("float32")
    D, I = index.search(q_emb, k=k)
    return [chunks[i] for i in I[0]]


def generate(query: str, context_chunks: list[dict]) -> str:
    context = "\n\n".join(
        f"[{c['section']}]\n{c['text']}"
        for c in context_chunks
    )

    prompt = f"""You are a medical education assistant. Answer using only the context below.
If the answer is not in the context, say "I don't have that information."

Context:
{context}

Question: {query}
Answer:"""

    response = requests.post(
    OLLAMA_URL,
    json={
        "model": OLLAMA_MODEL,
        "prompt": prompt,
        "stream": False,
        "options": {
            "num_thread": 8  # set to your CPU core count
        }
    },
    timeout=300  # increase timeout, CPU is slower
)
    response.raise_for_status()
    return response.json()["response"]


def rag(query: str) -> str:
    chunks_retrieved = retrieve(query)
    return generate(query, chunks_retrieved)


if __name__ == "__main__":
    query = "What are the danger signs of a newborn?"
    print(rag(query))