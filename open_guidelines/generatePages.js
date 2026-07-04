// generatePages.js
const fs = require('fs');
const path = require('path');

const assetsDir = path.join(__dirname, 'assets');
const outputFile = path.join(__dirname, 'assetManifest.js');

const INCLUDED_EXTS = ['.html', '.htm', '.css', '.png', '.jpg', '.jpeg', '.gif', '.svg', '.webp'];

function walk(dir, baseDir, results = []) {
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      walk(fullPath, baseDir, results);
    } else if (INCLUDED_EXTS.includes(path.extname(entry.name).toLowerCase())) {
      const relativePath = path.relative(baseDir, fullPath).split(path.sep).join('/');
      results.push(relativePath);
    }
  }
  return results;
}

const allFiles = walk(assetsDir, assetsDir).sort();

const lines = allFiles.map(f => `  '${f}': require('./assets/${f}'),`);
const output = `const assetManifest = {\n${lines.join('\n')}\n};\n\nexport default assetManifest;\n`;

fs.writeFileSync(outputFile, output);
console.log(`Generated manifest with ${allFiles.length} files (HTML, CSS, images)`);