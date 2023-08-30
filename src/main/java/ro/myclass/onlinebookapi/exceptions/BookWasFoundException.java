package ro.myclass.onlinebookapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookWasFoundException extends RuntimeException{

        public BookWasFoundException() {
            super("Book was found");
        }
}
