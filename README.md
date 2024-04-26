# [APIs CALLS MOCK](https://www.postman.com/test-team-develhope/workspace/library-excercise-springboot/collection/33167522-6de04644-6c08-4376-b996-ea07a1e62178)
# Biblio<br>
# Obiettivo:<br>
Creare un'applicazione Spring per gestire una lista di libri con operazioni CRUD e logica di business.<br>
# L'applicazione deve consentire di:<br>
- Aggiungere un nuovo libro.<br>
- Visualizzare l'elenco completo dei libri.<br>
- Visualizzare i dettagli di un libro specifico.<br>
- Modificare i dettagli di un libro esistente.<br>
- Rimuovere un libro dall'elenco.<br>
- Gestire lo stato di disponibilità di un libro (prestare o restituire).<br>
# La logica di business deve includere:<br>
- Controllo sulla disponibilità del libro prima di prestare.<br>
- Controllo sulla restituzione dei libri in prestito.<br>
- Controllo sul numero totale di prestiti di un libro.<br>
# Classi e funzionamento<br>
# Classe Book:<br>
- Attributi: id, titolo, autore, anno di pubblicazione, disponibilità.<br>
- Metodi: getter e setter per tutti gli attributi.<br>
# Classe BookService:<br>
- Interfaccia contenente i metodi per la logica di business sui libri.<br>
- Metodi:<br>
 List getAllBooks(): Restituisce l'elenco completo dei libri.<br>
 Book getBookById(int id): Restituisce un libro specifico per ID.<br>
 Book addBook(Book book): Aggiunge un nuovo libro.<br>
 Book updateBook(int id, Book book): Aggiorna i dettagli di un libro.<br>
 Book deleteBook(int id): Rimuove un libro dall'elenco.<br>
 Book lendBook(int id): Gestisce il prestito di un libro.<br>
 Book returnBook(int id): Gestisce il ritorno di un libro prestato.<br>
# Classe BookController:<br>
- Controller per gestire le richieste HTTP relative ai libri.<br>
- Metodi:<br>
 GET /books/readAll: Restituisce l'elenco completo dei libri.<br>
 GET /books/readSingle/{id}: Restituisce i dettagli di un libro specifico.<br>
 POST /books/create: Aggiunge un nuovo libro.<br>
 PUT /books/update/{id}: Aggiorna i dettagli di un libro esistente.<br>
 DELETE /books/delete/{id}: Rimuove un libro dall'elenco.<br>
 PUT /books/{id}/lend: Gestisce il prestito di un libro.<br>
 PUT /books/{id}/return: Gestisce il ritorno di un libro prestato.<br>
#
Implementazione della logica di business nei metodi di BookService.<br>
Configurazione di Spring per gestire le dipendenze e le richieste HTTP.<br>
Test delle operazioni CRUD e della logica di business con JUnit e MockMvc.<br>
Questa traccia fornisce un'idea generale di come progettare un'applicazione CRUD con Spring e includere la logica di business per il controllo del prestito dei libri. Puoi aggiungere ulteriori dettagli e funzionalità in base alle esigenze specifiche del progetto
