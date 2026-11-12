package chnu.edu.inno.exception;

import java.util.List;

/** Некоректні вхідні дані -> 400 Bad Request. */
public class InvalidRequestException extends RuntimeException {

    private final List<String> details;

    public InvalidRequestException(String message) {
        super(message);
        this.details = List.of();
    }

    public InvalidRequestException(String message, List<String> details) {
        super(message);
        this.details = List.copyOf(details);
    }

    public List<String> getDetails() {
        return details;
    }
}
