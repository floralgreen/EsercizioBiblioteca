package esercizi.EsercizioBiblioteca.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.DTO.BaseResponse;
import esercizi.EsercizioBiblioteca.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "This API given a Book by requestbody saves it in the DB and returns the saved Book")
    public ResponseEntity<BaseResponse> addBook(@RequestBody Book book) {
        BaseResponse response = bookService.addBook(book);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{id}/lend")
    @Operation(summary = "This API given a bookId returns a response JSON OK the lended Book / NOTFOUND if the resource does not exists / BAD REQUEST if the book is not available")
    public ResponseEntity<BaseResponse> lendBook(@PathVariable int id) {
        BaseResponse response = bookService.lendBook(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{id}/return")
    @Operation(summary = "This API given a bookId returns a response JSON OK the returned Book / NOTFOUND if the resource does not exists / BAD REQUEST if the book is already available")
    public ResponseEntity<BaseResponse> returnBook(@PathVariable int id) {
        BaseResponse response = bookService.returnBook(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all")
    @Operation(summary = "This API returns a List with all the books in the DB")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "This API given a bookId returns a response with status OK and the book found / NOT FOUND if the resource does not exists")
    public ResponseEntity<BaseResponse> getBookById(@PathVariable int id) {
        BaseResponse response = bookService.getBookById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "This API given a bookId and a Book to update returns a response with status OK and the book updated / NOT FOUND if the resource does not exists")
    public ResponseEntity<BaseResponse> updateBook(@PathVariable int id, @RequestBody Book book) {
        BaseResponse response = bookService.updateBook(id, book);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "This API given a bookId returns a response with STATUS OK and the deleted Book / NOT FOUND if the resource does not exists")
    public ResponseEntity<BaseResponse> deleteBook(@PathVariable int id) {
        BaseResponse response = bookService.deleteBookById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



}
