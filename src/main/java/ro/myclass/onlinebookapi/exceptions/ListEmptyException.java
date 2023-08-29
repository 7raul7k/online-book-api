package ro.myclass.onlinebookapi.exceptions;

public class ListEmptyException extends RuntimeException{

    public ListEmptyException() {
        super("List is empty");
    }
}
