package hello.jdbc.repository.ex;

public class MyDBException extends RuntimeException {

    public void MyDbException() {
    }
    public MyDBException(String message) {
        super(message);
    }
    public MyDBException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyDBException(Throwable cause) {
        super(cause);
    }
}
