package esercizi.EsercizioBiblioteca.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.DTO.BaseResponse;
import esercizi.EsercizioBiblioteca.entities.enums.RecordStatusEnum;
import esercizi.EsercizioBiblioteca.repositories.BookRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BaseResponse addBook(Book book) {
        Book bookCreated = bookRepository.save(book);
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_OK, "OK", "Book Created!", bookCreated);
        return response;
    }

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

    public List<Book> getAllBooks() {
        return bookRepository.findAllActiveBooks();
    }

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

    public BaseResponse lendBook(Integer id) {
        BaseResponse response = new BaseResponse(HttpServletResponse.SC_NOT_FOUND, "NOT FOUND","Book doesn't exists!", Optional.empty());
        Optional<Book> bookOptional = bookRepository.findActiveBookById(id);

        if (bookOptional.isPresent()) {

            if (bookOptional.get().isAvailable()){
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


}
