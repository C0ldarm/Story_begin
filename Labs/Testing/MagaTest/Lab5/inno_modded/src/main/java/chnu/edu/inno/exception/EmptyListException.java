package chnu.edu.inno.exception;

/** Колекція порожня -> 404 Not Found з окремим повідомленням. */
public class EmptyListException extends RuntimeException {

    public EmptyListException(String message) {
        super(message);
    }
}
