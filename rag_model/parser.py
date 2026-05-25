import json
import re
import requests
from pathlib import Path
from bs4 import BeautifulSoup
import time

MODEL = "gpt-oss:120b-cloud"
HTML_DIR = Path("html")
OUTPUT_DIR = Path("processed")
OUTPUT_DIR.mkdir(exist_ok=True)

MAX_CHARS = 12_000  # ~3k tokens of headroom for llama3's 4k ctx
MAX_RETRIES = 3

SCHEMA_KEYS = {"chapter", "learning_objectives", "sections"}

PROMPT = """
genereate a structured json for this content with the sections, learning objectives, subsections, and image links
"""



def call_ollama(prompt: str) -> str:
    for attempt in range(1, MAX_RETRIES + 1):
        try:
            response = requests.post(
                "http://localhost:11434/api/generate",
                json={"model": MODEL, "prompt": prompt, "stream": False},
                timeout=120,
            )
            response.raise_for_status()
            return response.json()["response"]
        except (requests.RequestException, KeyError) as e:
            print(f"  Attempt {attempt}/{MAX_RETRIES} failed: {e}")
            if attempt < MAX_RETRIES:
                time.sleep(2 ** attempt)
    raise RuntimeError("Ollama unreachable after retries")



def extract_json(raw):

    cleaned = raw.strip()

    # remove markdown fences anywhere
    cleaned = re.sub(r'```json', '', cleaned)
    cleaned = re.sub(r'```', '', cleaned)

    # find JSON object
    start = cleaned.find('{')
    end = cleaned.rfind('}')

    if start == -1 or end == -1:
        raise ValueError("No JSON object found.")

    cleaned = cleaned[start:end+1]
    try:
        return json.loads(cleaned)
    except:
        return -1


def validate_schema(doc: dict) -> bool:
    return SCHEMA_KEYS.issubset(doc.keys())


all_documents = []

for html_file in HTML_DIR.glob("*.html"):
    print(f"Processing {html_file.name}")

    html_content = html_file.read_text(encoding="utf-8", errors="ignore")



 
    raw_output = call_ollama(PROMPT + html_content)
    parsed = extract_json(raw_output)
    if parsed == -1:
        print(f"Error parsing {html_file.name}")
        continue


    parsed["_source_file"] = html_file.name

    output_file = OUTPUT_DIR / (html_file.stem + ".json")
    output_file.write_text(json.dumps(parsed, indent=4), encoding="utf-8")

    all_documents.append(parsed)

corpus_path = OUTPUT_DIR / "medical_corpus.json"
corpus_path.write_text(json.dumps(all_documents, indent=4), encoding="utf-8")

failed = [d for d in all_documents if "error" in d]
print(f"\nDone. {len(all_documents)} files processed, {len(failed)} failed.")