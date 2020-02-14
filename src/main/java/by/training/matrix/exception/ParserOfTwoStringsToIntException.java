package by.training.matrix.exception;

public class ParserOfTwoStringsToIntException extends Exception {
    public ParserOfTwoStringsToIntException() {
    }

    public ParserOfTwoStringsToIntException(String message) {
        super(message);
    }

    public ParserOfTwoStringsToIntException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserOfTwoStringsToIntException(Throwable cause) {
        super(cause);
    }

    public ParserOfTwoStringsToIntException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
