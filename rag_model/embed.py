import json
import numpy as np
from sentence_transformers import SentenceTransformer

MODEL_NAME = "all-MiniLM-L6-v2"

model = SentenceTransformer(MODEL_NAME)

with open("chunks.json", "r", encoding="utf-8") as f:
    chunks = json.load(f)

texts = [c["text"] for c in chunks]

print("Embedding chunks...")

embeddings = model.encode(
    texts,
    show_progress_bar=True,
    convert_to_numpy=True
)

np.save("embeddings.npy", embeddings)

with open("chunk_metadata.json", "w", encoding="utf-8") as f:
    json.dump(chunks, f, indent=2)

print("Done.")