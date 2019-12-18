package br.com.tiago.labs_and_exams.frameworkAndDrivers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class ResponseCustom<T> {

    private ApiStatusEnum status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDescription;

    private T body;

    public ResponseCustom<T> responseError(ResponseCustom<T> response, String errorDescription, int errorCode) {
        response.setStatus(ApiStatusEnum.ERROR);
        response.setErrorCode(String.valueOf(errorCode));
        response.setErrorDescription(errorDescription);
        return response;
    }

    public ResponseCustom<T> responseSuccess(ResponseCustom<T> response, T body) {
        response.setBody(body);
        response.setStatus(ApiStatusEnum.OK);
        return response;
    }
}

