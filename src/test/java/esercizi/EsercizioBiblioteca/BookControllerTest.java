package esercizi.EsercizioBiblioteca;

import com.fasterxml.jackson.databind.ObjectMapper;
import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.enums.RecordStatusEnum;
import esercizi.EsercizioBiblioteca.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void createBook() throws Exception {
        //set up
        Book book = bookRepository.save(mockBook());


        String bookJson = objectMapper.writeValueAsString(book);

        this.mockMvc.perform(post("/books/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(book.getId())))
                .andReturn();

        //verify that the book is created in the DB
        assertThat(bookRepository.count()).isEqualTo(1);

        //clear the DB
        bookRepository.deleteAll();

    }

    @Test
    void retrieveBookById() throws Exception {
        //SETUP
        // Create a new Book and save it in the database
        Book savedBook = bookRepository.save(mockBook());


        // Perform a GET request to retrieve the Book by ID
        mockMvc.perform(get("/books/find/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.id", is(savedBook.getId())))
                .andExpect(jsonPath("$.data.title", is(savedBook.getTitle())))
                .andExpect(jsonPath("$.data.author", is(savedBook.getAuthor())))
                .andExpect(jsonPath("$.data.releaseYear", is(savedBook.getReleaseYear())))
                .andReturn();

        //clean the database
        bookRepository.deleteAll();
    }

    @Test
    void retrieveAllBooksInDB() throws Exception {
        //SETUP
        List<Book> savedBooks = new ArrayList<>();
        savedBooks.add(bookRepository.save(mockBook()));
        savedBooks.add(bookRepository.save(mockBook()));

        MvcResult result = this.mockMvc.perform(get("/books/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();


        List<Book> responseBookList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);

        assertThat(responseBookList.size()).isEqualTo(savedBooks.size());

        //clean DB
        bookRepository.deleteAll();
    }

    @Test
    void editBook() throws Exception {
        //set up
        Book bookSaved = bookRepository.save(mockBook());

        Book bookUpdated = new Book();
        bookUpdated.setTitle("Test Title Updated");
        bookUpdated.setAuthor("Test Author Updated");

        String bookJson = objectMapper.writeValueAsString(bookUpdated);

        this.mockMvc.perform(put("/books/edit/{id}", bookSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(bookSaved.getId())))
                .andExpect(jsonPath("$.data.title", is(bookUpdated.getTitle())))
                .andExpect(jsonPath("$.data.author", is(bookUpdated.getAuthor())))
                .andReturn();

        //clear the DB
        bookRepository.deleteAll();
    }

    @Test
    void deleteBook() throws Exception {
        //set up
        Book bookSaved = bookRepository.save(mockBook());


        MvcResult result = this.mockMvc.perform(delete("/books/delete/{id}", bookSaved.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(bookSaved.getId())))
                .andReturn();

        //Recupero l'entità disattivata e verifico che sia stata effettivamente disattivata
        Optional<Book> deletedBook = bookRepository.findById(bookSaved.getId());
        if (deletedBook.isPresent()){
            assertThat(deletedBook.get().getRecordStatusEnum()).isEqualTo(RecordStatusEnum.D);
        }

        //clear the DB
        bookRepository.deleteAll();
    }

    @Test
    void lendBook() throws Exception{
        Book bookSaved = bookRepository.save(mockBook());
        boolean lend = false;


        this.mockMvc.perform(post("/books/{id}/lend", bookSaved.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(bookSaved.getId())))
                .andExpect(jsonPath("$.data.available", is(lend)))
                .andReturn();

        //clear the DB
        bookRepository.deleteAll();
    }

    @Test
    void returnBook() throws Exception {
        Book bookSaved = mockBook();
        bookSaved.setAvailable(false);
        Book lendBook = bookRepository.save(bookSaved);

        boolean returned = true;

        this.mockMvc.perform(post("/books/{id}/return", bookSaved.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(bookSaved.getId())))
                .andExpect(jsonPath("$.data.available", is(returned)))
                .andReturn();

        //clear the DB
        bookRepository.deleteAll();
    }

    //Utility method for setup
    private Book mockBook() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setReleaseYear(2000);
        return book;
    }


}
