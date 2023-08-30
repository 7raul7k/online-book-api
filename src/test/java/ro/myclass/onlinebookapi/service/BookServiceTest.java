package ro.myclass.onlinebookapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlinebookapi.dto.BookDTO;
import ro.myclass.onlinebookapi.exceptions.ListEmptyException;
import ro.myclass.onlinebookapi.models.Book;
import ro.myclass.onlinebookapi.repo.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @Captor
    private ArgumentCaptor<Book> argumentCaptor;

    @Test
    public void getAllBooks(){
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

        doReturn(books).when(bookRepo).getAllBooks();

        assertEquals(books,bookService.getAllBooks());
    }

    @Test
    public void getAllBooksError(){
        List<Book> books = new ArrayList<>();

        doReturn(new ArrayList<>()).when(bookRepo).getAllBooks();

        assertThrows(ListEmptyException.class,()->bookService.getAllBooks());
    }


    @Test
    public void addBook(){
        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName(book.getName());

        bookService.addBook(book);

       verify(bookRepo,times(1)).save(argumentCaptor.capture());

         assertEquals(book1,argumentCaptor.getValue());
    }

    @Test
    public void addBookError(){
        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.of(book1)).when(bookRepo).getBookByName(book.getName());

        assertThrows(Exception.class,()->bookService.addBook(book));
    }

    @Test
    public void deleteBook(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName(book.getName());

        bookService.deleteBook(book.getName());

        verify(bookRepo,times(1)).delete(book);
    }

    @Test
    public void deleteBookError(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName(book.getName());

        assertThrows(Exception.class,()->bookService.deleteBook(book.getName()));
    }

    @Test
    public void updateBook(){
        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        Book book1 = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.of(book1)).when(bookRepo).getBookByName(book.getName());

        bookService.updateBook(book);

        verify(bookRepo,times(1)).saveAndFlush(book1);
    }

    @Test
    public void updateBookError(){
        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName(book.getName());

        this.bookService.updateBook(book);


        assertThrows(Exception.class,()->bookService.updateBook(book));
    }

    @Test
    public void getBookByName(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName(book.getName());

        assertEquals(book,bookService.getBookByName(book.getName()));
    }

    @Test
    public void getBookByNameError(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName(book.getName());

        assertThrows(Exception.class,()->bookService.getBookByName(book.getName()));
    }

    @Test
    public void getBookByAuthor(){
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

        doReturn(books).when(bookRepo).getBookByAuthor(book.getAuthor());

        assertEquals(books,bookService.getBookByAuthor(book.getAuthor()));
    }

    @Test
    public void getBookByAuthorError(){
        List<Book> books = new ArrayList<>();

        doReturn(new ArrayList<>()).when(bookRepo).getBookByAuthor("author");

        assertThrows(Exception.class,()->bookService.getBookByAuthor("author"));
    }

    @Test
    public void getBookByGenre(){
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

        doReturn(books).when(bookRepo).getBookByGenre(book.getGenre());

        assertEquals(books,bookService.getBookByGenre(book.getGenre()));
    }

    @Test
    public void getBookByGenreError(){
        List<Book> books = new ArrayList<>();

        doReturn(new ArrayList<>()).when(bookRepo).getBookByGenre("genre");

        assertThrows(Exception.class,()->bookService.getBookByGenre("genre"));
    }

    @Test
    public void getBookByYear(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
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
                .year(2001)
                .build();
        Book book3 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre3")
                .year(2001)
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

        doReturn(books).when(bookRepo).getBookByYear(book.getYear());

        assertEquals(books,bookService.getBookByYear(book.getYear()));
    }

    @Test
    public void getBookByYearError(){
        List<Book> books = new ArrayList<>();

        doReturn(new ArrayList<>()).when(bookRepo).getBookByYear(2000);

        assertThrows(Exception.class,()->bookService.getBookByYear(2000));
    }

    @Test
    public void getBookByNameAndGenre(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByNameAndGenre(book.getName(),book.getGenre());

        assertEquals(book,bookService.getBookByNameAndGenre(book.getName(),book.getGenre()));
    }

    @Test
    public void getBookByNameAndGenreError(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByNameAndGenre(book.getName(),book.getGenre());

        assertThrows(Exception.class,()->bookService.getBookByNameAndGenre(book.getName(),book.getGenre()));
    }

    @Test
    public void getBookByNameAndYear(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByNameAndYear(book.getName(),book.getYear());

        assertEquals(book,bookService.getBookByNameAndYear(book.getName(),book.getYear()));
    }

    @Test
    public void getBookByNameAndYearError(){
        Book book = Book.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2001)
                .build();

        doReturn(Optional.empty()).when(bookRepo).getBookByNameAndYear(book.getName(),book.getYear());

        assertThrows(Exception.class,()->bookService.getBookByNameAndYear(book.getName(),book.getYear()));
    }

    @Test
    public void getBookByGenreAndYear(){

       //generate 4 books with same genre and year
        Book book = Book.builder()
                .name("name1")
                .author("author1")
                .genre("genre")
                .year(2001)
                .build();
        Book book1 = Book.builder()
                .name("name2")
                .author("author2")
                .genre("genre")
                .year(2001)
                .build();
        Book book2 = Book.builder()
                .name("name3")
                .author("author3")
                .genre("genre")
                .year(2001)
                .build();
        Book book3 = Book.builder()
                .name("name4")
                .author("author4")
                .genre("genre")
                .year(2001)
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

        doReturn(books).when(bookRepo).getBookByGenreAndYear(book.getGenre(),book.getYear());

        assertEquals(books,bookService.getBookByGenreAndYear(book.getGenre(),book.getYear()));
       }

       @Test
       public void getBookByGenreAndYearError(){
              List<Book> books = new ArrayList<>();

              doReturn(new ArrayList<>()).when(bookRepo).getBookByGenreAndYear("genre",2000);

              assertThrows(Exception.class,()->bookService.getBookByGenreAndYear("genre",2000));
       }




}