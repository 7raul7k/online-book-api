package ro.myclass.onlinebookapi.exceptions;

public class BookWasFoundException extends RuntimeException{

        public BookWasFoundException() {
            super("Book was found");
        }
}
