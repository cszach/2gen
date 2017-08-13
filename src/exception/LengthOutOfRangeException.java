package exception;

public class LengthOutOfRangeException extends Exception {
    public LengthOutOfRangeException() {
        super("Value for length is out of permitted range");
    }
}
