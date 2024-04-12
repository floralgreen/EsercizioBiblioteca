# API CALLS MOCKED
[https://www.postman.com/test-team-develhope/workspace/library-excercise-springboot/collection/33167522-6de04644-6c08-4376-b996-ea07a1e62178](APIs)

Traccia:
Obiettivo: Creare un'applicazione Spring per gestire una lista di libri con operazioni CRUD e 
logica di business per il controllo del prestito dei libri.
Requisiti:
1. Ogni libro deve avere un titolo, un autore, un anno di pubblicazione e uno stato di 
disponibilità (prestato o disponibile).
2. L'applicazione deve consentire di:
o Aggiungere un nuovo libro.
o Visualizzare l'elenco completo dei libri.
o Visualizzare i dettagli di un libro specifico.
o Modificare i dettagli di un libro esistente.
o Rimuovere un libro dall'elenco.
o Gestire lo stato di disponibilità di un libro (prestare o restituire).
3. La logica di business deve includere:
o Controllo sulla disponibilità del libro prima di prestare.
o Controllo sul numero massimo di libri prestabili per utente.
o Controllo sulla restituzione dei libri in prestito.
Implementazione:
1. Classe Book:
o Attributi: id, titolo, autore, anno di pubblicazione, disponibilità.
o Metodi: getter e setter per tutti gli attributi.
2. Classe BookService:
o Interfaccia contenente i metodi per la logica di business sui libri.
o Metodi:
 List<Book> getAllBooks(): Restituisce l'elenco completo dei libri.
 Book getBookById(int id): Restituisce un libro specifico per ID.
 void addBook(Book book): Aggiunge un nuovo libro.
 void updateBook(int id, Book book): Aggiorna i dettagli di un libro.
 void deleteBook(int id): Rimuove un libro dall'elenco.
 void lendBook(int id): Gestisce il prestito di un libro.
 void returnBook(int id): Gestisce il ritorno di un libro prestato.
3. Classe BookController:
o Controller per gestire le richieste HTTP relative ai libri.
o Metodi:
 GET /books: Restituisce l'elenco completo dei libri.
 GET /books/{id}: Restituisce i dettagli di un libro specifico.
 POST /books: Aggiunge un nuovo libro.
 PUT /books/{id}: Aggiorna i dettagli di un libro esistente.
 DELETE /books/{id}: Rimuove un libro dall'elenco.
 POST /books/{id}/lend: Gestisce il prestito di un libro.
 POST /books/{id}/return: Gestisce il ritorno di un libro prestato.
4. Implementazione della logica di business nei metodi di BookService.
5. Configurazione di Spring per gestire le dipendenze e le richieste HTTP.
6. Test delle operazioni CRUD e della logica di business con JUnit e MockMvc.
Questa traccia fornisce un'idea generale di come progettare un'applicazione CRUD con Spring e 
includere la logica di business per il controllo del prestito dei libri. Puoi aggiungere ulteriori dettagli 
e funzionalità in base alle esigenze specifiche del progetto
