//script that generates the HTML require

const fs = require('fs');
const path = require('path');

const assetsDir = path.join(__dirname, 'assets');
const outputFile = path.join(__dirname, 'pagesMap.js');

const htmlFiles = fs.readdirSync(assetsDir)
  .filter(f => f.endsWith('.html') || f.endsWith('.htm'))
  .sort();

const lines = htmlFiles.map(f => `  '${f}': require('./assets/${f}'),`);

const output = `const pages = {\n${lines.join('\n')}\n};\n\nexport default pages;\n`;

fs.writeFileSync(outputFile, output);
console.log(`Generated ${htmlFiles.length} entries in pagesMap.js`);