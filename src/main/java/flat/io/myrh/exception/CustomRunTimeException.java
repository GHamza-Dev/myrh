package flat.io.myrh.exception;

public class CustomRunTimeException extends RuntimeException{
    public CustomRunTimeException() {
    }

    public CustomRunTimeException(String message) {
        super(message);
    }

    public CustomRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
