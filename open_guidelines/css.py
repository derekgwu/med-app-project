import os
import glob

css = open('assets/include/layout.css').read()
style_block = f'<style>\n{css}\n</style>\n'

for filepath in glob.glob('assets/**/*.htm*', recursive=True):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    if '<body>' in content and '<style>' not in content:
        content = content.replace('<body>', f'<body>\n{style_block}', 1)
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f'Injected: {filepath}')