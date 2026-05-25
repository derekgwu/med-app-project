import json
import numpy as np
import faiss
from sentence_transformers import SentenceTransformer

model = SentenceTransformer("all-MiniLM-L6-v2")

index = faiss.read_index("medical.index")

with open("chunk_metadata.json", "r", encoding="utf-8") as f:
    chunks = json.load(f)

query = "danger signs newborn"

q_emb = model.encode([query]).astype("float32")

D, I = index.search(q_emb, k=5)

for i in I[0]:
    print("\n---")
    print(chunks[i]["text"])
    print(chunks[i]["section"])