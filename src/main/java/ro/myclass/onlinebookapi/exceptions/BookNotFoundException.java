package ro.myclass.onlinebookapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException() {
        super("Book not found");
    }
}
