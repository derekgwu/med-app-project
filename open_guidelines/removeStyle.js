// removeStyleTags.js
const fs = require('fs');
const path = require('path');

const TARGET_DIR = path.join(__dirname, 'assets'); // adjust to your HTML source root

// Matches <style ...> ... </style>, case-insensitive, across multiple lines
const STYLE_TAG_REGEX = /<style\b[^>]*>[\s\S]*?<\/style>/gi;

function findHtmlFiles(dir, fileList = []) {
    const entries = fs.readdirSync(dir, { withFileTypes: true });
    for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
            findHtmlFiles(fullPath, fileList);
        } else if (entry.isFile() && entry.name.toLowerCase().endsWith('.html')) {
            fileList.push(fullPath);
        }
    }
    return fileList;
}

function removeStyleTags(filePath) {
    const original = fs.readFileSync(filePath, 'utf8');
    const cleaned = original.replace(STYLE_TAG_REGEX, '');

    if (cleaned !== original) {
        fs.writeFileSync(filePath, cleaned, 'utf8');
        console.log(`✔ Removed <style> block(s): ${filePath}`);
        return true;
    }
    return false;
}

function main() {
    const htmlFiles = findHtmlFiles(TARGET_DIR);
    console.log(`Found ${htmlFiles.length} HTML file(s) under ${TARGET_DIR}\n`);

    let modifiedCount = 0;
    for (const file of htmlFiles) {
        if (removeStyleTags(file)) modifiedCount++;
    }

    console.log(`\nDone. Modified ${modifiedCount} of ${htmlFiles.length} file(s).`);
}

main();