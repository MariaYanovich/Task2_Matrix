package task2.exception;

public class ReaderOfTwoStringsException extends Exception {
    public ReaderOfTwoStringsException() {
    }

    public ReaderOfTwoStringsException(String message) {
        super(message);
    }

    public ReaderOfTwoStringsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderOfTwoStringsException(Throwable cause) {
        super(cause);
    }

    public ReaderOfTwoStringsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
