package esercizi.EsercizioBiblioteca.services;

import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.DTO.BaseResponse;
import esercizi.EsercizioBiblioteca.entities.enums.RecordStatusEnum;
import esercizi.EsercizioBiblioteca.repositories.BookRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private final Integer MAX_BOOKS_LEND = 50;

    /**
     *
     * @param book given a book to create
     * @return a BaseResponse containing the status of the response, the Book just created on the DB and a confirmation message
     */
    public BaseResponse addBook(Book book) {
        Book bookCreated = bookRepository.save(book);
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_OK, "OK", "Book Created!", bookCreated);
        return response;
    }

    /**
     *
     * @param id given a bookId
     * @return a BaseResponse with status 200 if the Book is found
     *          or a BaseResponse with status NOT_FOUND if the Book doesn't exists in the DB
     */
    public BaseResponse getBookById(Integer id) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> book = bookRepository.findActiveBookById(id);
        if (book.isPresent()) {
            //Set response as OK
            response.setStatus(HttpServletResponse.SC_OK);
            response.setStatusMessage("OK");
            response.setMessage("Book found");
            response.setData(book.get());
        }
        return response;
    }

    /**
     *
     * @return a List with all the Books in the DB
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAllActiveBooks();
    }

    /**
     *
     * @param id given a bookId that you want to update
     * @param book with updated infos
     * @return a BaseResponse with STATUS OK, the entity updated
     *          a BaseResponse with NOT FOUND
     */
    public BaseResponse updateBook(Integer id, Book book) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> bookOptional = bookRepository.findActiveBookById(id);
        if (bookOptional.isPresent()) {
            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setAuthor(book.getAuthor());
            bookOptional.get().setReleaseYear(book.getReleaseYear());
            bookRepository.save(bookOptional.get());

            //Set the response as OK
            response.setStatus(HttpServletResponse.SC_OK);
            response.setStatusMessage("OK");
            response.setMessage("Book updated");
            response.setData(bookOptional.get());
        }
        return response;
    }

    /**
     *
     * @param id bookId
     * @return BaseResponse with STATUS OK and the Book lend
     *          BaseResponse with STATUS NOT FOUND if the resource does not exist
     *          BaseResponse with STATUS BAD_REQUEST if the Book is already lend
     */
    public BaseResponse lendBook(Integer id) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> bookOptional = bookRepository.findActiveBookById(id);



        if (bookOptional.isPresent()) {

            if (bookOptional.get().isAvailable() && checkMaxBooksAvailability()){
                bookOptional.get().setAvailable(false);
                bookRepository.save(bookOptional.get());

                //Set response OK book è stato prestato
                response.setStatus(HttpServletResponse.SC_OK);
                response.setStatusMessage("OK");
                response.setMessage("Book Lend");
                response.setData(bookOptional.get());
            } else {
                //Set BAD REQUEST because book is already lended
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setStatusMessage("BAD_REQUEST");
                response.setMessage("Book is not available");
                response.setData(bookOptional.get());
            }
        }
        return response;
    }

    /**
     *
     * @param id given a bookId
     * @return BaseResponse with STATUS OK and the Book returned
     *          BaseResponse with STATUS NOT FOUND if the resource does not exist
     *          BaseResponse with STATUS BAD_REQUEST if the Book is already available
     */
    public BaseResponse returnBook(Integer id) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> bookOptional = bookRepository.findActiveBookById(id);

        if (bookOptional.isPresent()) {
            if(bookOptional.get().isAvailable()){
                //Set BAD REQUEST perché il libro è già presente
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setStatusMessage("BAD_REQUEST");
                response.setMessage("Book cannot be returned, because it is already available");
                response.setData(bookOptional.get());
            } else {

                bookOptional.get().setAvailable(true);
                bookRepository.save(bookOptional.get());

                //Set OK perché il libro è stato ritornato
                response.setStatus(HttpServletResponse.SC_OK);
                response.setStatusMessage("OK");
                response.setMessage("Book returned");
                response.setData(bookOptional.get());
            }
        }
        return response;
    }

    /**
     *
     * @param id given a bookId
     * @return the deleted Book
     */
    public BaseResponse deleteBookById(Integer id) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> book = bookRepository.findActiveBookById(id);

        if (book.isPresent()) {
            book.get().setRecordStatusEnum(RecordStatusEnum.D);
            bookRepository.save(book.get());

            response.setStatus(HttpServletResponse.SC_OK);
            response.setStatusMessage("OK");
            response.setMessage("Book deleted");
            response.setData(book.get());
        }
        return response;
    }

    /**
     *
     * @return true if the actual list of books lend is less than the MAX_BOOKS_LEND(50) field
     */
    private boolean checkMaxBooksAvailability(){
        Integer lendedBooksActual = bookRepository.findAllActiveBooksOnAvailability(false).size();
        return lendedBooksActual < MAX_BOOKS_LEND;
    }


}
