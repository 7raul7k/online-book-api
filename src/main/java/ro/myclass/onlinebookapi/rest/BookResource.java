package ro.myclass.onlinebookapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlinebookapi.dto.BookDTO;
import ro.myclass.onlinebookapi.models.Book;
import ro.myclass.onlinebookapi.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Slf4j
@CrossOrigin
public class BookResource {

    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = this.bookService.getAllBooks();

        log.info("REST request to get all books {}", bookList);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody BookDTO book) throws InterruptedException {
        this.bookService.addBook(book);

        log.info("REST request to add a book {}", book);

        Thread.sleep(5000);

        return new ResponseEntity<>("Book was added", HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{name}")
    public ResponseEntity<String> deleteBook(@PathVariable String name) {
        this.bookService.deleteBook(name);

        log.info("REST request to delete a book by name {}", name);

        return new ResponseEntity<>("Book was deleted", HttpStatus.OK);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = this.bookService.getBookById(id);

        log.info("REST request to get a book by id {}", id);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByName/{name}")
    public ResponseEntity<Book> getBookByName(@PathVariable String name) {
        Book book = this.bookService.getBookByName(name);

        log.info("REST request to get a book by name {}", name);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByAuthor/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        List<Book> book = this.bookService.getBookByAuthor(author);

        log.info("REST request to get a book by author {}", author);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByGenre/{genre}")
    public ResponseEntity<List<Book>> getBookByGenre(@PathVariable String genre) {

        List<Book> bookList = this.bookService.getBookByGenre(genre);

        log.info("REST request to get a book by genre {}", genre);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/getBookByYear/{year}")
    public ResponseEntity<List<Book>> getBookByYear(@PathVariable int year) {

        List<Book> bookList = this.bookService.getBookByYear(year);

        log.info("REST request to get a book by year {}", year);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody BookDTO book) {
        this.bookService.updateBook(book);

        log.info("REST request to update a book {}", book);

        return new ResponseEntity<>("Book was updated", HttpStatus.OK);
    }

    @GetMapping("/getBookByNameAndAuthor/{name}/{author}")
    public ResponseEntity<Book> getBookByNameAndAuthor(@PathVariable String name,@PathVariable String author) {
        Book book = this.bookService.getBookByNameAndAuthor(name,author);

        log.info("REST request to get a book by name and author {}", name,author);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByNameAndGenre")
    public ResponseEntity<Book> getBookByNameAndGenre(@RequestParam String name,@RequestParam String genre) {
        Book book = this.bookService.getBookByNameAndGenre(name,genre);

        log.info("REST request to get a book by name and genre {}", name,genre);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByNameAndYear")
    public ResponseEntity<Book> getBookByNameAndYear(@RequestParam String name,@RequestParam int year) {
        Book book = this.bookService.getBookByNameAndYear(name,year);

        log.info("REST request to get a book by name and year {}", name,year);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByGenreAndYear")
    public ResponseEntity<List<Book>> getBookByGenreAndYear(@RequestParam String genre,@RequestParam int year) {

        List<Book> bookList = this.bookService.getBookByGenreAndYear(genre,year);

        log.info("REST request to get a book by genre and year {}", genre,year);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/getBookByNameAndAuthorAndYear")
    public ResponseEntity<Book> getBookByNameAndAuthorAndYear(@RequestParam String name,@RequestParam String author,@RequestParam int year) {
        Book book = this.bookService.getBookByNameAndAuthorAndYear(name,author,year);

        log.info("REST request to get a book by name and author and year {}", name,author,year);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBookByNameAndAuthorAndGenre")
    public ResponseEntity<Book> getBookByNameAndAuthorAndGenre(@RequestParam String name,@RequestParam String author,@RequestParam String genre) {
        Book book = this.bookService.getBookByNameAndAuthorAndGenre(name,author,genre);

        log.info("REST request to get a book by name and author and genre {}", name,author,genre);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

  





}
