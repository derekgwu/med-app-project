import numpy as np
import faiss

embeddings = np.load("embeddings.npy").astype("float32")

dim = embeddings.shape[1]

index = faiss.IndexFlatL2(dim)

index.add(embeddings)

faiss.write_index(index, "medical.index")

print("FAISS index built:", index.ntotal)