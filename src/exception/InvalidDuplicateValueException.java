package exception;

public class InvalidDuplicateValueException extends Exception {
    public InvalidDuplicateValueException() {
        super("Invalid amount for duplication");
    }
}
