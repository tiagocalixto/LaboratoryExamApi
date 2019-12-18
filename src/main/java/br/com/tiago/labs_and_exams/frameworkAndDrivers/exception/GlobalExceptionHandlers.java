package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlers {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ErrorResponse handleNotFoundException(final NoSuchElementException ex, HttpServletRequest request) {

        log.error("Register not Found!", ex);
        return this.setErrorResponse(HttpStatus.NOT_FOUND, "Register not found!", ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex, HttpServletRequest request) {

        log.error("Server Error", ex);
        return this.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected internal server error occurred!", ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {

        log.error("Error - Duplicate key", ex);
        return this.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error - Duplicate key!", ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ErrorResponse httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        log.error("Unsupported HTTP method", ex);
        return this.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unsupported HTTP method!", ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleException(Exception ex, HttpServletRequest request) {

        log.error("Server Error ", ex);
        return this.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Server error!", ex.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    @ResponseBody
    public ErrorResponse bancoDadosException(DataBaseException ex, HttpServletRequest request) {
        log.error("Error on data Base!", ex);
        return this.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error on Data Base!", ex.getMessage(), request);
    }

    private ErrorResponse setErrorResponse(HttpStatus status, String errorDescription, String stackTrace, HttpServletRequest request) {

        return ErrorResponse.builder()
                .errorStatus(status.name())
                .errorCode(status.value())
                .errorDescription(errorDescription)
                .url(request.getRequestURL().toString())
                .httpMethod(request.getMethod())
                .stackTrace(stackTrace)
                .build();
    }

    @Getter
    @Setter
    @Builder
    public static class ErrorResponse {

        private final String errorStatus;
        private final int errorCode;
        private final String errorDescription;
        private final String url;
        private final String httpMethod;
        private final String stackTrace;
    }
}
