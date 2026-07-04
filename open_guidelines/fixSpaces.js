// fixSpaces.js
const fs = require('fs');
const path = require('path');

const assetsDir = path.join(__dirname, 'assets');

// Recursively walk the assets folder, return list of full file paths
function walk(dir, results = []) {
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      walk(fullPath, results);
    } else {
      results.push(fullPath);
    }
  }
  return results;
}

const allFiles = walk(assetsDir);

// Build a rename map: oldName -> newName (filename only, not full path)
const renameMap = {}; // e.g. "Paeds Mech Vent.htm" -> "Paeds_Mech_Vent.htm"

for (const filePath of allFiles) {
  const dir = path.dirname(filePath);
  const base = path.basename(filePath);
  if (base.includes(' ')) {
    const newBase = base.replace(/ /g, '_');
    const newPath = path.join(dir, newBase);
    fs.renameSync(filePath, newPath);
    renameMap[base] = newBase;
    console.log(`Renamed: ${base} -> ${newBase}`);
  }
}

console.log(`\nTotal files renamed: ${Object.keys(renameMap).length}\n`);

// Now update every HTML file's href/src references to match renamed files
const htmlFiles = walk(assetsDir).filter(f => /\.html?$/i.test(f));

let totalReplacements = 0;

for (const filePath of htmlFiles) {
  let content = fs.readFileSync(filePath, 'utf8');
  const before = content;

  for (const [oldName, newName] of Object.entries(renameMap)) {
    // Match href="...oldName" or src="...oldName" (with any path prefix before the filename)
    const escapedOld = oldName.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
    const regex = new RegExp(`((?:href|src)=["'][^"']*?)${escapedOld}(["'])`, 'g');
    content = content.replace(regex, `$1${newName}$2`);
  }

  if (content !== before) {
    fs.writeFileSync(filePath, content, 'utf8');
    const count = (before.match(new RegExp(Object.keys(renameMap).map(n =>
      n.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')).join('|'), 'g')) || []).length;
    totalReplacements += count;
    console.log(`Updated references in: ${path.basename(filePath)}`);
  }
}

console.log(`\nDone. Files renamed: ${Object.keys(renameMap).length}, HTML files updated with new references.`);