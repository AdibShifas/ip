package flores.exception;

/**
 * Represents exceptions specific to the Flores application.
 * This class is used to handle errors that occur during command parsing or task execution.
 */
public class FloresException extends Exception {

    /**
     * Constructs a FloresException with the specified detail message.
     *
     * @param message The error message explaining the cause of the exception.
     */
    public FloresException(String message) {
        super(message);
    }
}