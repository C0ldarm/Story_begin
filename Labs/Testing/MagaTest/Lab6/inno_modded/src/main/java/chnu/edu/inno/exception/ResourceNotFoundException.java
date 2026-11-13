package chnu.edu.inno.exception;

/** Сутність не знайдено -> 404 Not Found. */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
