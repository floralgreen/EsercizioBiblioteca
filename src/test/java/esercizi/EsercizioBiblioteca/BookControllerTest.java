package esercizi.EsercizioBiblioteca;

import com.fasterxml.jackson.databind.ObjectMapper;
import esercizi.EsercizioBiblioteca.controllers.BookController;
import esercizi.EsercizioBiblioteca.repositories.BookRepository;
import esercizi.EsercizioBiblioteca.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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


}
