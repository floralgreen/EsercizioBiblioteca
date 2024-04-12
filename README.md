# [APIs CALLS MOCK](https://www.postman.com/test-team-develhope/workspace/library-excercise-springboot/collection/33167522-6de04644-6c08-4376-b996-ea07a1e62178)

# Traccia:<br>

**Obiettivo:**<br>
Creare un'applicazione Spring per gestire una lista di libri con operazioni CRUD e 
logica di business per il controllo del prestito dei libri.
<br>
<br>
**Requisiti:**
1. Ogni libro deve avere un titolo, un autore, un anno di pubblicazione e uno stato di 
disponibilità (prestato o disponibile).<br>
2. L'applicazione deve consentire di:<br>
o Aggiungere un nuovo libro.<br>
o Visualizzare l'elenco completo dei libri.<br>
o Visualizzare i dettagli di un libro specifico.<br>
o Modificare i dettagli di un libro esistente.<br>
o Rimuovere un libro dall'elenco.<br>
o Gestire lo stato di disponibilità di un libro (prestare o restituire).<br>
3. La logica di business deve includere:<br>
o Controllo sulla disponibilità del libro prima di prestare.<br>
o Controllo sul numero massimo di libri prestabili per utente.<br>
o Controllo sulla restituzione dei libri in prestito.<br>
**Implementazione:**<br>
1. Classe Book:<br>
o Attributi: id, titolo, autore, anno di pubblicazione, disponibilità.<br>
o Metodi: getter e setter per tutti gli attributi.<br>
2. Classe BookService:<br>
o Interfaccia contenente i metodi per la logica di business sui libri.<br>
o Metodi:<br>
 List<Book> getAllBooks(): Restituisce l'elenco completo dei libri.<br>
 Book getBookById(int id): Restituisce un libro specifico per ID.<br>
 void addBook(Book book): Aggiunge un nuovo libro.<br>
 void updateBook(int id, Book book): Aggiorna i dettagli di un libro.<br>
 void deleteBook(int id): Rimuove un libro dall'elenco.<br>
 void lendBook(int id): Gestisce il prestito di un libro.<br>
 void returnBook(int id): Gestisce il ritorno di un libro prestato.<br>
3. Classe BookController:<br>
o Controller per gestire le richieste HTTP relative ai libri.<br>
o Metodi:<br>
 GET /books: Restituisce l'elenco completo dei libri.<br>
 GET /books/{id}: Restituisce i dettagli di un libro specifico.<br>
 POST /books: Aggiunge un nuovo libro.<br>
 PUT /books/{id}: Aggiorna i dettagli di un libro esistente.<br>
 DELETE /books/{id}: Rimuove un libro dall'elenco.<br>
 POST /books/{id}/lend: Gestisce il prestito di un libro.<br>
 POST /books/{id}/return: Gestisce il ritorno di un libro prestato.<br>
4. Implementazione della logica di business nei metodi di BookService.<br>
5. Configurazione di Spring per gestire le dipendenze e le richieste HTTP.<br>
6. Test delle operazioni CRUD e della logica di business con JUnit e MockMvc.<br>
Questa traccia fornisce un'idea generale di come progettare un'applicazione CRUD con Spring e 
includere la logica di business per il controllo del prestito dei libri. Puoi aggiungere ulteriori dettagli 
e funzionalità in base alle esigenze specifiche del progetto
