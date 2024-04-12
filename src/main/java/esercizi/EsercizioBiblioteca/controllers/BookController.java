package esercizi.EsercizioBiblioteca.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.DTO.BaseResponse;
import esercizi.EsercizioBiblioteca.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addBook(@RequestBody Book book) {
        BaseResponse response = bookService.addBook(book);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{id}/lend")
    public ResponseEntity<BaseResponse> lendBook(@PathVariable int id) {
        BaseResponse response = bookService.lendBook(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<BaseResponse> returnBook(@PathVariable int id) {
        BaseResponse response = bookService.returnBook(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBookById(@PathVariable int id) {
        BaseResponse response = bookService.getBookById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBook(@PathVariable int id, @RequestBody Book book) {
        BaseResponse response = bookService.updateBook(id, book);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBook(@PathVariable int id) {
        BaseResponse response = bookService.deleteBookById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



}
