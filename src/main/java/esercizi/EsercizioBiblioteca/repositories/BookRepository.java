package esercizi.EsercizioBiblioteca.repositories;

import esercizi.EsercizioBiblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM books AS b WHERE b.id = :id", nativeQuery = true)
    Optional<Book> findActiveBookById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM books AS b WHERE b.record_status = 'A'", nativeQuery = true)
    List<Book> findAllActiveBooks();

}
