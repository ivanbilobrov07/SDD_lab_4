package Exceptions;

/**
 * Exception used to signal that a certain required condition or collection
 * is unexpectedly empty.
 */
public class EmptyException extends Exception{
    /**
     * Constructs an EmptyException with a detailed message explaining the empty condition.
     *
     * @param message A string providing specifics about the empty condition that triggered the exception.
     */
    public EmptyException(String message) {
        super(message);
    }
}
