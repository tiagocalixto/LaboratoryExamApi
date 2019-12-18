package br.com.tiago.labs_and_exams.frameworkAndDrivers.exception;

import br.com.tiago.labs_and_exams.frameworkAndDrivers.response.ApiStatusEnum;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.response.ResponseCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ApiExceptionHandler {

    public ResponseEntity<Object> handleDataBaseException(DataBaseException error, String entity) {

        log.error("Error on data base - Api "+ entity +" reason {} - {}", error.getClass().getSimpleName(),
                error.getError().getMessage() == null ? error.getMessage() : error.getError().getMessage(), error.getError());
        ResponseCustom<Object> response = new ResponseCustom<>();
        response.setStatus(ApiStatusEnum.ERROR);

        response.setErrorCode("");
        response.setErrorDescription(error.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Object> handleServiceException(ServiceException error, String entity) {

        log.error("Error on service - Api "+ entity +" reason {} - {}", error.getClass().getSimpleName(),
                error.getError().getMessage() == null ? error.getMessage() : error.getError().getMessage(), error.getError());
        ResponseCustom<Object> response = new ResponseCustom<>();
        response.setStatus(ApiStatusEnum.ERROR);

        response.setErrorCode("");
        response.setErrorDescription(error.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException error, String entity) {

        log.error("Error on Service - Api "+ entity +" reason {} - {}", error.getClass().getSimpleName(),
                error.getMessage(), error);
        ResponseCustom<Object> response = new ResponseCustom<>();
        response.setStatus(ApiStatusEnum.ERROR);

        response.setErrorCode("");
        response.setErrorDescription(error.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> handleException(Exception error, String entity) {

        log.error("Error on Service - Api "+ entity +" reason {} - {}", error.getClass().getSimpleName(),
                error.getMessage(), error);
        ResponseCustom<Object> response = new ResponseCustom<>();
        response.setStatus(ApiStatusEnum.ERROR);

        response.setErrorCode("");
        response.setErrorDescription("Error on execution of operation.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

