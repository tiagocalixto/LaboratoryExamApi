package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

public class JsonUtilMappingException extends JsonUtilException{
    private static final long serialVersionUID = 1L;

    public JsonUtilMappingException() {
        super();
    }

    public JsonUtilMappingException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JsonUtilMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonUtilMappingException(String message) {
        super(message);
    }

    public JsonUtilMappingException(Throwable cause) {
        super(cause);
    }
}
