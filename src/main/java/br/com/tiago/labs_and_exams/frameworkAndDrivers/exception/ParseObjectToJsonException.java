package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

import java.io.Serializable;

public class ParseObjectToJsonException extends JsonUtilException implements Serializable{
    private static final long serialVersionUID = 1L;

    public ParseObjectToJsonException() {
        super();
    }

    public ParseObjectToJsonException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParseObjectToJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseObjectToJsonException(String message) {
        super(message);
    }

    public ParseObjectToJsonException(Throwable cause) {
        super(cause);
    }
}
