import json
from pathlib import Path

INPUT_DIR = Path("processed")          # <-- make sure this folder really contains the JSON files
all_chunks = []


def add_chunk(text, chapter, section, source, kind):
    """Normalise the payload and append a dict to the global list."""
    if text is None:
        return

    # turn a list into a newline‑separated string
    if isinstance(text, list):
        text = "\n".join(str(x) for x in text)

    # turn a dict into a compact JSON string
    if isinstance(text, dict):
        text = json.dumps(text, ensure_ascii=False)

    # discard empty / whitespace‑only strings
    if not text or not text.strip():
        return

    all_chunks.append({
        "text": text.strip(),
        "chapter": chapter,
        "section": section,
        "source": source,
        "type": kind
    })


def process_section(section, chapter, source, parent=None):
    """
    Recursively walk one section (or subsection) and push every
    piece of information to `add_chunk`.
    """
    title = section.get("title", "")
    full_title = f"{parent} > {title}" if parent else title

    # ----- free text -------------------------------------------------
    add_chunk(section.get("content", ""), chapter, full_title, source, "content")

    # ----- lists ------------------------------------------------------
    lists = section.get("lists", {})
    if isinstance(lists, dict):
        ordered = lists.get("ordered", [])
        unordered = lists.get("unordered", [])

        if ordered:
            add_chunk("Ordered list:\n" + "\n".join(ordered),
                      chapter, full_title, source, "ordered_list")
        if unordered:
            add_chunk("Bullet points:\n" + "\n".join(unordered),
                      chapter, full_title, source, "unordered_list")

    # ----- tables -----------------------------------------------------
    for table in section.get("tables", []):
        caption = table.get("caption", "")
        headers = table.get("headers")
        rows = table.get("rows", [])

        # whole‑table chunk
        table_txt = f"Table: {caption}\n"
        if headers:
            table_txt += "Headers: " + " | ".join(headers) + "\n"
        for row in rows:
            table_txt += f"Row: {row}\n"
        add_chunk(table_txt, chapter, full_title, source, "table")

        # one chunk per row (great for retrieval)
        for row in rows:
            add_chunk(f"{caption}\n{row}", chapter, full_title, source, "table_row")

    # ----- images -----------------------------------------------------
    for img in section.get("image_links", []):
        if isinstance(img, dict):
            img_txt = f"Image: {img.get('description','')} {img.get('src','')}"
        elif isinstance(img, str):
            img_txt = f"Image: {img}"
        else:
            continue
        add_chunk(img_txt, chapter, full_title, source, "image")

    # ----- recurse into subsections ---------------------------------
    for subsection in section.get("subsections", []):
        process_section(subsection, chapter, source, full_title)


# ----------------------------------------------------------------------
# 1️⃣   Walk every JSON file in the folder
# ----------------------------------------------------------------------
json_files = list(INPUT_DIR.glob("*.json"))
print(f"Found {len(json_files)} JSON file(s) in {INPUT_DIR!s}")

for file_path in json_files:
    print(f"\n--- Processing {file_path.name} ---")
    with open(file_path, encoding="utf-8") as f:
        doc = json.load(f)

    # the file may contain a single dict *or* a list of dicts
    docs = doc if isinstance(doc, list) else [doc]

    for d in docs:
        chapter = d.get("title", "")
        source = file_path.name

        # ----- learning objectives ------------------------------------
        objectives = d.get("learning_objectives", [])
        if objectives:
            add_chunk("Learning objectives:\n" + "\n".join(objectives),
                      chapter, "Learning Objectives", source, "learning_objectives")

        # ----- top‑level sections ------------------------------------
        for section in d.get("sections", []):
            process_section(section, chapter, source)


# ----------------------------------------------------------------------
# 2️⃣  Write the chunks out
# ----------------------------------------------------------------------
out_path = Path("chunks.json")
out_path.write_text(json.dumps(all_chunks, indent=2, ensure_ascii=False), encoding="utf-8")
print(f"\n✅ Finished – {len(all_chunks)} chunks written to {out_path}")
