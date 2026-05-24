import json
import requests
from pathlib import Path
from bs4 import BeautifulSoup
import time

MODEL = "llama3"
HTML_DIR = Path("html")
OUTPUT_DIR = Path("processed")
OUTPUT_DIR.mkdir(exist_ok=True)

MAX_CHARS = 12_000  # ~3k tokens of headroom for llama3's 4k ctx
MAX_RETRIES = 3

SCHEMA_KEYS = {"chapter", "learning_objectives", "sections"}

PROMPT = """You are a medical textbook extraction system.
Convert the provided HTML content into clean structured JSON.

Rules:
- Preserve chapter hierarchy, learning objectives, sections, tables, and references.
- Do NOT hallucinate content.
- Output ONLY valid JSON, no prose, no markdown fences.

Schema:
{
  "chapter": "",
  "learning_objectives": [],
  "sections": []
}

Content:
"""


def strip_html(html: str) -> str:
    """Extract readable text/structure from HTML."""
    soup = BeautifulSoup(html, "html.parser")
    for tag in soup(["script", "style", "nav", "footer", "header"]):
        tag.decompose()
    return soup.get_text(separator="\n", strip=True)


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


def extract_json(raw: str) -> dict:
    """Parse JSON, stripping markdown fences if present."""
    cleaned = raw.strip().removeprefix("```json").removeprefix("```").removesuffix("```").strip()
    return json.loads(cleaned)


def validate_schema(doc: dict) -> bool:
    return SCHEMA_KEYS.issubset(doc.keys())


all_documents = []

for html_file in HTML_DIR.glob("*.html"):
    print(f"Processing {html_file.name}")

    html_content = html_file.read_text(encoding="utf-8", errors="ignore")
    text_content = strip_html(html_content)

    if len(text_content) > MAX_CHARS:
        print(f"  Truncating {len(text_content)} chars to {MAX_CHARS}")
        text_content = text_content[:MAX_CHARS]

    try:
        raw_output = call_ollama(PROMPT + text_content)
        parsed = extract_json(raw_output)

        if not validate_schema(parsed):
            raise ValueError(f"Schema mismatch, got keys: {list(parsed.keys())}")

    except (json.JSONDecodeError, ValueError, RuntimeError) as e:
        print(f"  Failed: {e}")
        parsed = {
            "file": html_file.name,
            "error": str(e),
            "raw_llm_output": raw_output if "raw_output" in dir() else None,
        }

    parsed["_source_file"] = html_file.name

    output_file = OUTPUT_DIR / (html_file.stem + ".json")
    output_file.write_text(json.dumps(parsed, indent=4), encoding="utf-8")

    all_documents.append(parsed)

corpus_path = OUTPUT_DIR / "medical_corpus.json"
corpus_path.write_text(json.dumps(all_documents, indent=4), encoding="utf-8")

failed = [d for d in all_documents if "error" in d]
print(f"\nDone. {len(all_documents)} files processed, {len(failed)} failed.")