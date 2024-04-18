package esercizi.EsercizioBiblioteca;

import com.fasterxml.jackson.databind.ObjectMapper;
import esercizi.EsercizioBiblioteca.controllers.BookController;
import esercizi.EsercizioBiblioteca.entities.Book;
import esercizi.EsercizioBiblioteca.entities.DTO.BaseResponse;
import esercizi.EsercizioBiblioteca.repositories.BookRepository;
import esercizi.EsercizioBiblioteca.services.BookService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads(){
    }

    @Test
    void createBook() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setReleaseYear(2000);

        String bookJson = objectMapper.writeValueAsString(book);

        MvcResult mvcResult = this.mockMvc.perform(post("/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)).andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }



}
