package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends JsonUtilException implements Serializable {

    public ResourceConflictException(String message) {
        super(message);
    }
}
