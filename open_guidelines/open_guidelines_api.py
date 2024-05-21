import requests
from bs4 import BeautifulSoup

def get_webpage_source(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.content, 'html.parser')
    html_source = soup.prettify()
    with open('./assets/triage.html', 'w') as file:
        file.write(html_source)
    return html_source
   

# Example usage
url = 'https://www.openguidelines.net/data/set_1011/html/C1_Triage.html'
source_code = get_webpage_source(url)
print(source_code)