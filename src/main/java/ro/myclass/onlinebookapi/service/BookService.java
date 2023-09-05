package ro.myclass.onlinebookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlinebookapi.dto.BookDTO;
import ro.myclass.onlinebookapi.exceptions.BookNotFoundException;
import ro.myclass.onlinebookapi.exceptions.BookWasFoundException;
import ro.myclass.onlinebookapi.exceptions.ListEmptyException;
import ro.myclass.onlinebookapi.models.Book;
import ro.myclass.onlinebookapi.repo.BookRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class BookService {

    private BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.getAllBooks();

        if (books.isEmpty()) {
            throw new ListEmptyException();
        }
        return books;
    }

    public void addBook(BookDTO book) {
        Optional<Book> book1 = bookRepo.getBookByName(book.getName());

        if (book1.isEmpty()) {
            Book book2 = Book.builder()
                    .name(book.getName())
                    .author(book.getAuthor())
                    .genre(book.getGenre())
                    .year(book.getYear())
                    .build();

            this.bookRepo.save(book2);
        } else {
            throw new BookWasFoundException();
        }
    }

    public void deleteBook(String name) {
        Optional<Book> book = bookRepo.getBookByName(name);

        if (book.isEmpty()) {
            throw new BookWasFoundException();
        } else {
            bookRepo.delete(book.get());
        }
    }

    public void updateBook( BookDTO book) {
        Optional<Book> book1 = bookRepo.getBookByName(book.getName());

        if (book1.isEmpty()) {
            throw new BookWasFoundException();
        } else {
            if (book.getAuthor() != null)
                book1.get().setAuthor(book.getAuthor());
        }if(book.getGenre() != null) {
            book1.get().setGenre(book.getGenre());
        }if(book.getYear() != 0) {
            book1.get().setYear(book.getYear());
        }

            bookRepo.saveAndFlush(book1.get());
        }
    

    public Book getBookByName(String name){
        Optional<Book> book = bookRepo.getBookByName(name);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public List<Book> getBookByAuthor(String author){
        List<Book> books = bookRepo.getBookByAuthor(author);
        if(books.isEmpty()){
            throw new ListEmptyException();
        }else{
            return books;
        }
    }

    public List<Book> getBookByGenre(String genre){
        List<Book> books = bookRepo.getBookByGenre(genre);
        if(books.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return books;
        }
    }

    public List<Book> getBookByYear(int year){
        List<Book> books = bookRepo.getBookByYear(year);
        if(books.isEmpty()){
            throw new ListEmptyException();
        }else{
            return books;
        }
    }

    public Book getBookByNameAndAuthor(String name,String author){
        Optional<Book> book = bookRepo.getBookByNameAndAuthor(name,author);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public Book getBookByNameAndGenre(String name,String genre){
        Optional<Book> book = bookRepo.getBookByNameAndGenre(name,genre);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public Book getBookByNameAndYear(String name,int year){
        Optional<Book> book = bookRepo.getBookByNameAndYear(name,year);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public Book getBookByNameAndAuthorAndGenre(String name,String author,String genre){
        Optional<Book> book = bookRepo.getBookByNameAndAuthorAndGenre(name,author,genre);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public Book getBookByNameAndAuthorAndYear(String name,String author,int year){
        Optional<Book> book = bookRepo.getBookByNameAndAuthorAndYear(name,author,year);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }

    public List<Book> getBookByGenreAndYear(String genre,int year){
        List<Book> books = bookRepo.getBookByGenreAndYear(genre,year);
        if(books.isEmpty()){
            throw new ListEmptyException();
        }else{
            return books;
        }
    }

    public Book getBookById(long id){
        Optional<Book> book = bookRepo.getBookById(id);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            return book.get();
        }
    }


}
