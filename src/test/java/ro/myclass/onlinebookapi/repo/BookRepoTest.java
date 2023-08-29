package ro.myclass.onlinebookapi.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlinebookapi.OnlineBookApiApplication;
import ro.myclass.onlinebookapi.models.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineBookApiApplication.class)
@Transactional
class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @BeforeEach
    public void clean() {
        bookRepo.deleteAll();
    }

    @Test
    public void getAllBooks() {

        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();
        Book book1 = Book.builder()
                .name("name1")
                .author("author1")
                .genre("genre1")
                .year(2001)
                .build();
        Book book2 = Book.builder()
                .name("name2")
                .author("author2")
                .genre("genre2")
                .year(2002)
                .build();
        Book book3 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre3")
                .year(2003)
                .build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        assertEquals(books, bookRepo.getAllBooks());
    }

    @Test
    public void getBookByName() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();


        bookRepo.save(book);


        assertEquals(book, bookRepo.getBookByName("name").get());
    }

    @Test
    public void getBookByAuthor() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name1")
                .author("author")
                .genre("genre1")
                .year(2001)
                .build();

        Book book2 = Book.builder()
                .name("name2")
                .author("author")
                .genre("genre2")
                .year(2002)
                .build();

        Book book3 = Book.builder()
                .name("name3")
                .author("author")
                .genre("genre3")
                .year(2003)
                .build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        assertEquals(books, bookRepo.getBookByAuthor("author"));

    }

    @Test
    public void getBookByNameAndAuthor() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookByNameAndAuthor("name","author").get());
    }

    @Test
    public void getBookById() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookById(book.getId()).get());
    }

    @Test
    public void getBookByGenre() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name1")
                .author("author1")
                .genre("genre")
                .year(2001)
                .build();

        Book book2 = Book.builder()
                .name("name2")
                .author("author2")
                .genre("genre")
                .year(2002)
                .build();

        Book book3 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre")
                .year(2003)
                .build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        assertEquals(books, bookRepo.getBookByGenre("genre"));
    }

    @Test
    public void getBookByYear() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name1")
                .author("author1")
                .genre("genre1")
                .year(2000)
                .build();

        Book book2 = Book.builder()
                .name("name2")
                .author("author2")
                .genre("genre2")
                .year(2000)
                .build();

        Book book3 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre3")
                .year(2000)
                .build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        assertEquals(books, bookRepo.getBookByYear(2000));
    }

    @Test
    public void getBookByNameAndGenre() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookByNameAndGenre("name","genre").get());
    }

    @Test
    public void getBookByNameAndYear() {
        Book book = Book.builder()
                .name("name")
                .author("author1")
                .genre("genre1")
                .year(2000)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookByNameAndYear("name",2000).get());
    }

    @Test
    public void getBookByGenreAndYear() {
        Book book = Book.builder()
                .name("name1")
                .author("author1")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name2")
                .author("author2")
                .genre("genre")
                .year(2000)
                .build();

        Book book2 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre")
                .year(2000)
                .build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);

        assertEquals(books, bookRepo.getBookByGenreAndYear("genre",2000));
    }

    @Test
    public void getBookByNameAndAuthorAndYear() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre1")
                .year(2000)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookByNameAndAuthorAndYear("name","author",2000).get());
    }

    @Test
    public void getBookByNameAndAuthorAndGenre() {
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        bookRepo.save(book);

        assertEquals(book, bookRepo.getBookByNameAndAuthorAndGenre("name","author","genre").get());
    }


}