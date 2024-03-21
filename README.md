# full-stack

## Introduzione 

Ultimamente mi è venuto da pensare a come poter implementare una nuova applicazione,
giusto per essere sicuro di non dare per scontato che le cose che sto usando adesso
siano per forza le migliori.
In più mi piacerebbe esplorare possibilità diverse, ad esempio verificando cosa succede
se decido di usare un baas (backend as a service).
Per fare questo non mi piaceva molto l' idea della classica Todo List, perché è 
un' applicazione troppo semplice e, secondo me, non è in grado di dare un buon riscontro sulla
bontà delle tecnologie scelte. 
Perciò ho deciso di provare con un' applicazione un po' più complessa della TODO, ma comunque gestibile
per poter fare, abbastanza velocemente, 3 o 4 implementazioni diverse.
Ho preso spunto da un caso reale: una casa piena di libri e la necessità di disfarsene.
Perciò, quella che voglio implementare, è un' applicazione web che permetta di salvare l' elenco dei libri posseduti,
creare una lista da poter mandare alle librerie per vedere se sono interessate, e fare alcune operazioni interessanti.
Ad esempio creare un bundle: cioè un raggruppamento di libri che vengano messi insieme sotto un titolo comune.
Le tecnologie che mi interessa testare riguardano soprattutto l' ecosistema Java e Spring, Angular e Typescript e, perché no,
anche una bella fetta di css.
Il motivo, profondamente meditato, è semplicemente che queste tecnologie mi piacciono.


## Specifiche dell' applicazione

Nell' applicazione esiste un solo ruolo, l' amministratore, che può fare tutto. Deve, prima di poter procedere con l' amministrazione dei libri, autenticarsi.
Ci sono le seguenti funzionalità:
  - vedere il proprio patrimonio di libri
    - dalla pagina iniziale si vede l' elenco dei libri posseduti
  - aggiungere un libro
    - aggiunge un libro nel DB
  - eliminare un libro
    - elimina un libro dal DB. Se il libro era presente anche in un bundle, lo toglie dal Bundle
  - creare un bundle
    - un bundle è un sottoinsieme di libri, raggruppati da un etichetta comune. Ad esempio si può creare il Bundle della narrativa italiana e includere tutti i libri di autori italiani. E' utile perché, per vendere o regalare i libri, spesso è utile raggrupparli per genere o, viceversa, se si è già raggiunto un accordo con una libreria si può creare il bundle per quella libreria per poi eliminare i libri in blocco, una volta venduti.
    Uno stesso libro può essere presente in più bundle, perché ci sono diverse possibilità per raggrupparlo e venderlo.
  - eliminare un bundle
    - questa operazione considera il bundle venduto perciò fa le seguenti cose:
        1. elimina il bundle
        2. elimina tutti i libri contenuti dall' elenco totale
        3. toglie da eventuali altri bundle i libri presenti in questo bundle.
  - sganciare i libri da un bundle
    - elimina semplicemente l' associazione dei libri al bundle, che non esisterà più. I libri, ovviamente, restano disponibili nell' elenco.
  - stampare un elenco
    - permette di stampare a video, plain text, un elenco di libri, magari per fare un copia e incolla e mandarlo via mail

## Base dati

Magari è esagerato chiamarla così. Ogni progetto dovrebbe avere un file init.sql con l' sql per generare le tabelle nel DB.
Molto in breve ci sono 2 tabelle per l' autenticazione: users e authorities.
Ce ne sono 3 per la logica dell' applicazione.
Una per i libri, una per i bundle e l' ultima per le associazioni tra libri e bundle.
Per ulteriori dettagli vedere il file init.sql dentro ai singoli progetti.

## Prima applicazione (ah i bei tempi andati)

Questa applicazione è un po' un' operazione di nostalgia, perché è fatta ricordando i tempi in cui le web app erano quasi tutte delle multi page application e le interazioni con il js erano limitate a qualche funzionalità di nicchia.
In questo caso, giusto per andare sul sicuro, non c' è una riga di js. Se guardate ai file html che vengono giù, vedrete che non ho mai incluso uno script js, né l' ho usato inline nell' html.

### Perché

L' ho fatto soprattutto per esperimento. Spesso, oggi, si dà per scontato che una web app debba essere una SPA (single page application) e si sceglie quella strada senza valutarne i pro e i contro. 
Volevo vedere cosa succedeva se invece tornavo a fare una MPA (multi page application)
Per farlo ho usato Spring, in combinazione con Spring Boot naturalmente, e il motore di template thymeleaf. Con questa stack si può costruire un' intera applicazione, come dimostra appunto il risultato.
In pratica, qualsiasi richiesta viene processata lato server, ed è sempre il server che assembla le pagine HTML con i dati recuperati da Spring, per poi servirle come contenuti statici al client che ne ha fatto richiesta.
Questo significa che qualsiasi operazione fatta scatena una richiesta e la pagina viene ricaricata completamente.
D' altra parte, il vantaggio, è che l' applicazione della sicurezza (tramite form di autenticazione in questo caso) è davevro semplice. L' applicazione è SEO friendly, perché non esegue codice lato client e ogni pagina è, come si suol dire, deep linkable (gli  url sono condivisibili).
Trovate tutto nella directory: book-give-away

## Seconda applicazione (HDA)

### Cosa diavolo è?

HDA sta per Hypermedia-Driven Application. Nel libro hypermedia systems (disponibile online) viene definita così: "A web application that uses hypermedia and hypermedia exchanges as its primary mechanism for communicating with a server."
Ok ma che cos' è un hypermedia e che cosa non lo è?
Hypermedia è qualsiasi mezzo (ad esempio del testo) che contiene al proprio interno dei riferimenti o link che permettono di interrompere la lettura
lineare e di saltare ad un altro contenuto (dello stesso mezzo o altro, ad esempio un link in un testo che porti ad un' immagine offre un contenuto di un altro mezzo).
L' esempio più diffuso e utile a noi in questo contesto è l' html.
L' html è un mezzo per sua natura hypermediale, tramite 2 tag specifici
```html
  <a href="#">vai al link</a>
  <form method="post" action="/server"></form>
```
 che permettono di navigare tra documenti e addirittura di modificarli. Questi 2 elementi però hanno dei limiti. Tanto per dirne una possono usare solo 2 metodi del protocollo di trasferimento HTTP, POST e GET.
Invece, uno snippet di codice come questo
```js
 fetch('url')
  .then(response => response.json())
  .then(data => updateUI(data))
```
non è un hypermedia. Questo javascript fa una cosa molto comune in questi giorni. Dal client, di solito un browser, chiama un determinato url per ottenere dei dati in cambio, di solito in formato JSON, poi li converte a sua volta in JSON, e da ultimo chiama una funzione che è responsabile dell' aggiornamento dell' UI sulla base dei dati ricevuti.
Questo, va da sé, rende strettamente accoppiati il client e il server, perché il client ha bisogno di conoscere il significato preciso dei dati che riceve e il modo per far reagire l' interfaccia alla ricezione di questi dati. Una modifica, lato server, ai dati restituiti molto probabilmente richiederà una modifica anche lato client.
Se invece di restituire dei dati il server rispondesse con un "pezzo" di html, o comunque qualcosa di hypermediale, l'accoppiamento sarebbe invece debole e modifiche al server potrebbero non richiedere modifiche al client.
Ad oggi il paradigma che va per la maggiore è quello SPA (single page application).
In pratica l' applicazione web è un' unica pagina che, una volta caricata, non richiede più un giro completo con il server, ma che si aggiorna semplicemente in alcune sue parti tramite interazioni con il server limitate ad alcune parti e guidate dal javascript.
Si può dire che le web app, oggigiorno, siano pesantemente javascript oriented, tanto che sono nati complessi framework per gestirle.
Il motivo principale è che questo approccio consente un' interattività molto spinta con l' UI che ormai è un requisito irrinunciabile della modernità.
Intendiamoci, alcuni producono ancora delle MPA(multi page application), ma sono le eccezioni.
Il lato negativo di questa faccenda è l' enorme complessità di sviluppo e dei framework che nascono per supportare questo paradigma, tanto che spesso si parla di javascript fatigue.
Anche il caricamento iniziale della App è rallentato dal fantastiliardo di javascript che è necessario caricare per interagire con l' applicazione.
Proprio per questo motivo molti cercano un approccio alternativo, come Richard Harris, creatore di Svelte, che cerca un approccio transizionale, cioè un compromesso tra un sistema hypermedia e una single page application.
Lo scopo di una hypermedia-driven application è quello di mettere insieme il livello hypermedia di un' applicazione multi page con la reattività di una single page application.
Si riuscirà nell' intento? Vedremo.