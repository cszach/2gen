package exception;

public class DirectoryDoesNotExistException extends Exception {
    public DirectoryDoesNotExistException() {
        super("Directory does not exist");
    }
}
