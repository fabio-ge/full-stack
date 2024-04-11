###############################################################
#######     AGGIUNGE L' HISTORY: USARE UNA VOLTA SOLA #########
###############################################################

import os
from bs4 import BeautifulSoup

# directories da spazzolare
base_dir = 'C:\\Users\\fabio\\Desktop\\Sviluppo\\htmx\\book_sell\\book-java-htmx\\src\\main\\resources\\templates'
dirs = [base_dir,f'{base_dir}\parts']


# Funzione per aggiungere hx-push-url="true" ai tag
def add_hx_push_url(file_path):
    with open(file_path, 'r+', encoding='utf-8') as file:
        soup = BeautifulSoup(file, 'html.parser')
        
        # Trova tutti i tag con attributi hx-get (per scelta non pusho nell' history gli altri metodi http)
        hx_tags = soup.find_all(lambda tag: any(tag.has_attr(attr) for attr in ['hx-get', 'th:hx-get']))
        
        # Aggiungi hx-push-url="true" a ciascun tag trovato
        for tag in hx_tags:
            tag['hx-push-url'] = 'true'
        
        # Ritorna al principio del file e sovrascrivi con il nuovo contenuto
        file.seek(0)
        file.write(str(soup))
        file.truncate()

# Itera su tutti i file nella directory e applica la funzione
for dir in dirs:
    for filename in os.listdir(dir):
        if filename.endswith('.html'):
            file_path = os.path.join(dir, filename)
            add_hx_push_url(file_path)

print('Modifica completata per tutti i file HTML.')
