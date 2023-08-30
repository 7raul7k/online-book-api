package ro.myclass.onlinebookapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.onlinebookapi.dto.BookDTO;
import ro.myclass.onlinebookapi.exceptions.BookNotFoundException;
import ro.myclass.onlinebookapi.exceptions.ListEmptyException;
import ro.myclass.onlinebookapi.models.Book;
import ro.myclass.onlinebookapi.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookResourceTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookResource bookResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(bookResource).build();
    }

    @Test
    public void getAllBooks() throws Exception{

        Faker faker = new Faker();

        List<Book> bookList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++){
            Book book = Book.builder()
                    .name(faker.book().title())
                    .author(faker.book().author())
                    .genre(faker.book().genre())
                    .year(faker.number().numberBetween(1900,2021))
                    .build();
            bookList.add(book);
        }

        doReturn(bookList).when(bookService).getAllBooks();

        restMockMvc.perform(get("/api/books/allBooks")).andExpect(content().string(objectMapper.writeValueAsString(bookList)));
    }

    @Test
    public void getAllBooksBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(bookService).getAllBooks();

        restMockMvc.perform(get("/api/books/allBooks")).andExpect(status().isBadRequest());
    }

    @Test
    public void addBook() throws Exception{

        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();


        doNothing().when(bookService).addBook(book);

        restMockMvc.perform(put("/api/books/addBook").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book))).andExpect(status().isOk());
    }

    @Test
    public void addBookBadRequest() throws Exception {

        BookDTO book = BookDTO.builder()
                .name("name")
                .author("author")
                .genre("genre")
                .year(2000)
                .build();

        doThrow(ListEmptyException.class).when(bookService).addBook(book);

        restMockMvc.perform(put("/api/books/addBook").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book))).andExpect(status().isBadRequest());
    }
    @Test
    public void deleteBook() throws Exception{

        doNothing().when(bookService).deleteBook("name");

        restMockMvc.perform(delete("/api/books/deleteBook/name")).andExpect(status().isOk());
    }

    @Test
    public void deleteBookBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(bookService).deleteBook("name");

        restMockMvc.perform(delete("/api/books/deleteBook/name")).andExpect(status().isBadRequest());
    }

    @Test
    public void getBookById() throws Exception{

        Faker faker = new Faker();

        Book book = Book.builder()
                .name(faker.book().title())
                .author(faker.book().author())
                .genre(faker.book().genre())
                .year(faker.number().numberBetween(1900,2021))
                .build();

        doReturn(book).when(bookService).getBookById(1);

        restMockMvc.perform(get("/api/books/getBookById/1")).andExpect(content().string(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void getBookByIdBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(bookService).getBookById(1);

        restMockMvc.perform(get("/api/books/getBookById/1")).andExpect(status().isBadRequest());
    }

    @Test
    public void getBookByName() throws Exception{

        Faker faker = new Faker();

        Book book = Book.builder()
                .name(faker.book().title())
                .author(faker.book().author())
                .genre(faker.book().genre())
                .year(faker.number().numberBetween(1900,2021))
                .build();

        doReturn(book).when(bookService).getBookByName("name");

        restMockMvc.perform(get("/api/books/getBookByName/name")).andExpect(content().string(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void getBookByNameBadRequest() throws Exception{

        doThrow(BookNotFoundException.class).when(bookService).getBookByName("name");

        restMockMvc.perform(get("/api/books/getBookByName/name")).andExpect(status().isBadRequest());
    }

     @Test
    public void getBookByAuthor() throws Exception {

         Faker faker = new Faker();

         List<Book> bookList = new ArrayList<>();

         for (int i = 0; i < 10; i++) {
             Book book = Book.builder()
                     .name(faker.book().title())
                     .author(faker.book().author())
                     .genre(faker.book().genre())
                     .year(faker.number().numberBetween(1900, 2021))
                     .build();
             bookList.add(book);
         }

         doReturn(bookList).when(bookService).getBookByAuthor("author");

         restMockMvc.perform(get("/api/books/getBookByAuthor/author")).andExpect(content().string(objectMapper.writeValueAsString(bookList)));

     }

        @Test
        public void getBookByAuthorBadRequest() throws Exception {
        doThrow(BookNotFoundException.class).when(bookService).getBookByAuthor("author");

        restMockMvc.perform(get("/api/books/getBookByAuthor/author")).andExpect(status().isBadRequest());
        }

        @Test
        public void getBookByGenre() throws Exception {

            Faker faker = new Faker();

            List<Book> bookList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Book book = Book.builder()
                        .name(faker.book().title())
                        .author(faker.book().author())
                        .genre(faker.book().genre())
                        .year(faker.number().numberBetween(1900, 2021))
                        .build();
                bookList.add(book);
            }

            doReturn(bookList).when(bookService).getBookByGenre("genre");

            restMockMvc.perform(get("/api/books/getBookByGenre/genre")).andExpect(content().string(objectMapper.writeValueAsString(bookList)));

        }

        @Test
        public void getBookByGenreBadRequest() throws Exception {
            doThrow(BookNotFoundException.class).when(bookService).getBookByGenre("genre");

            restMockMvc.perform(get("/api/books/getBookByGenre/genre")).andExpect(status().isBadRequest());
        }

        @Test
        public void getBookByYear() throws Exception {

            Faker faker = new Faker();

            List<Book> bookList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Book book = Book.builder()
                        .name(faker.book().title())
                        .author(faker.book().author())
                        .genre(faker.book().genre())
                        .year(faker.number().numberBetween(1900, 2021))
                        .build();
                bookList.add(book);
            }

            doReturn(bookList).when(bookService).getBookByYear(2000);

            restMockMvc.perform(get("/api/books/getBookByYear/2000")).andExpect(content().string(objectMapper.writeValueAsString(bookList)));

        }

        @Test
        public void getBookByYearBadRequest() throws Exception {
            doThrow(BookNotFoundException.class).when(bookService).getBookByYear(2000);

            restMockMvc.perform(get("/api/books/getBookByYear/2000")).andExpect(status().isBadRequest());
        }

        @Test
        public void getBookByNameAndAuthor() throws Exception{

            Faker faker = new Faker();

            Book book = Book.builder()
                    .name(faker.book().title())
                    .author(faker.book().author())
                    .genre(faker.book().genre())
                    .year(faker.number().numberBetween(1900,2021))
                    .build();

            doReturn(book).when(bookService).getBookByNameAndAuthor("name","author");

            restMockMvc.perform(get("/api/books/getBookByNameAndAuthor/name/author")).andExpect(content().string(objectMapper.writeValueAsString(book)));
        }

        @Test
        public void getBookByNameAndAuthorBadRequest() throws Exception{

            doThrow(BookNotFoundException.class).when(bookService).getBookByNameAndAuthor("name","author");

            restMockMvc.perform(get("/api/books/getBookByNameAndAuthor/name/author")).andExpect(status().isBadRequest());
        }

        @Test
        public void updateBook() throws Exception{

            BookDTO book = BookDTO.builder()
                    .name("name")
                    .author("author")
                    .genre("genre")
                    .year(2000)
                    .build();

            doNothing().when(bookService).updateBook(book);

            restMockMvc.perform(put("/api/books/updateBook").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book))).andExpect(status().isOk());
        }

        @Test
        public void updateBookBadRequest() throws Exception{

            BookDTO book = BookDTO.builder()
                    .name("name")
                    .author("author")
                    .genre("genre")
                    .year(2000)
                    .build();

            doThrow(BookNotFoundException.class).when(bookService).updateBook(book);

            restMockMvc.perform(put("/api/books/updateBook").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book))).andExpect(status().isBadRequest());
        }

}