package ro.myclass.onlinebookapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlinebookapi.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

    @Query("select b from Book b where b.name = ?1")
    Optional<Book> getBookByName(String name);

    @Query("select b from Book b where b.author = ?1")
    List<Book> getBookByAuthor(String author);

    @Query("select b from Book b where b.name = ?1 AND b.author = ?2")
    Optional<Book> getBookByNameAndAuthor(String name,String author);

    @Query("select b from Book b where b.id = ?1")
    Optional<Book> getBookById(long id);

    @Query("select b from Book b where b.genre = ?1")
    List<Book> getBookByGenre(String genre);

    @Query("select b from Book b where b.year = ?1")
    List<Book> getBookByYear(int year);

    @Query("select b from Book b where b.name = ?1 AND b.genre = ?2")
    Optional<Book> getBookByNameAndGenre(String name,String genre);

    @Query("select b from Book b where b.name = ?1 AND b.year = ?2")
    Optional<Book> getBookByNameAndYear(String name,int year);

    @Query("select b from Book b where b.genre = ?1 AND b.year = ?2")
    List<Book> getBookByGenreAndYear(String genre,int year);

    @Query("select b from Book b where b.name = ?1 AND b.author = ?2 AND b.year = ?3")
    Optional<Book> getBookByNameAndAuthorAndYear(String name,String author,int year);

    @Query("select b from Book b where b.name = ?1 AND b.author = ?2 AND b.genre = ?3")
    Optional<Book> getBookByNameAndAuthorAndGenre(String name,String author,String genre);

    

}
