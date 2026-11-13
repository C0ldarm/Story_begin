package chnu.edu.inno.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Єдина структура відповіді API.
 *
 * @param success true для статусів 2xx
 * @param status  числовий HTTP-код
 * @param message зрозуміле для клієнта текстове пояснення
 * @param data    корисне навантаження (сутність, сторінка, перелік помилок) або null
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, int status, String message, T data) {

    public static <T> ApiResponse<T> of(int status, String message, T data) {
        return new ApiResponse<>(status >= 200 && status < 300, status, message, data);
    }

    public static <T> ApiResponse<T> of(int status, String message) {
        return of(status, message, null);
    }
}
