const fs = require('fs');
const path = require('path');

// Directory containing your generated HTML files
const ASSETS_DIR = path.join(__dirname, 'assets');

// Extensions to scan
const HTML_EXTENSIONS = ['.html', '.htm'];

let filesChanged = 0;
let totalReplacements = 0;

function walk(dir) {
  const entries = fs.readdirSync(dir, { withFileTypes: true });
  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      walk(fullPath);
    } else if (HTML_EXTENSIONS.includes(path.extname(entry.name).toLowerCase())) {
      processFile(fullPath);
    }
  }
}

function processFile(filePath) {
  const original = fs.readFileSync(filePath, 'utf8');

  // Replace ../images with images (handles src="../images/..." and href="../images/...")
  const updated = original.replace(/\.\.\/images/g, 'images');

  if (updated !== original) {
    const matches = (original.match(/\.\.\/images/g) || []).length;
    fs.writeFileSync(filePath, updated, 'utf8');
    filesChanged++;
    totalReplacements += matches;
    console.log(`Fixed ${matches} reference(s) in: ${path.relative(ASSETS_DIR, filePath)}`);
  }
}

if (!fs.existsSync(ASSETS_DIR)) {
  console.error(`Assets directory not found at: ${ASSETS_DIR}`);
  process.exit(1);
}

walk(ASSETS_DIR);

console.log(`\nDone. ${filesChanged} file(s) changed, ${totalReplacements} total replacement(s).`);