package chnu.edu.inno.exception;

/** Запитано неіснуючу сторінку (page >= totalPages) -> 404 Not Found. */
public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException(int page, int totalPages) {
        super("Сторінки з номером " + page + " не існує. Доступні сторінки: від 0 до "
                + (totalPages - 1) + " (усього сторінок: " + totalPages + ")");
    }
}
