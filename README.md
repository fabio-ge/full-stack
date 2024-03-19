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
Molto in breve ci sono 2 tabella per l' autenticazione: users e authorities.
Ce ne sono 3 per la logica dell' applicazione.
Una per i libri, una per i bundle e l' ultima per le associazioni tra libri e bundle.
Per ulteriori dettagli vedere il file init.sql dentro ai singoli progetti.

## Prima applicazione (ah i bei tempi andati)

