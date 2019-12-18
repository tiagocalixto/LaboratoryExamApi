package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

public class JsonUtilException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JsonUtilException() {
        super();
    }

    public JsonUtilException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JsonUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonUtilException(String message) {
        super(message);
    }

    public JsonUtilException(Throwable cause) {
        super(cause);
    }
}
