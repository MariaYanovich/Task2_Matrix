package task2.exception;

public class InitializerOfMatrixSizeAndThreadsNumberException extends Exception {
    public InitializerOfMatrixSizeAndThreadsNumberException() {
    }

    public InitializerOfMatrixSizeAndThreadsNumberException(String message) {
        super(message);
    }

    public InitializerOfMatrixSizeAndThreadsNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializerOfMatrixSizeAndThreadsNumberException(Throwable cause) {
        super(cause);
    }

    public InitializerOfMatrixSizeAndThreadsNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
