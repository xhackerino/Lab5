package Exception;

/**
 * Исключение при пустом вводе.
 */
public class EmptyIOException extends Exception {
    public EmptyIOException() {
        super("Empty IO");
    }
}
