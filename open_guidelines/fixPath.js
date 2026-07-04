// fixPaths.js
const fs = require('fs');
const path = require('path');

const assetsDir = path.join(__dirname, 'assets');
const htmlFiles = fs.readdirSync(assetsDir)
  .filter(f => f.endsWith('.html') || f.endsWith('.htm'));

let totalReplacements = 0;

for (const file of htmlFiles) {
  const filePath = path.join(assetsDir, file);
  let content = fs.readFileSync(filePath, 'utf8');
  const before = content;

  // Strip the incorrect "../assets/" prefix wherever it appears
  content = content.replace(/\.\.\/assets\//g, '');

  if (content !== before) {
    const count = (before.match(/\.\.\/assets\//g) || []).length;
    totalReplacements += count;
    fs.writeFileSync(filePath, content, 'utf8');
    console.log(`Fixed ${count} reference(s) in ${file}`);
  }
}

console.log(`\nDone. Total replacements: ${totalReplacements}`);