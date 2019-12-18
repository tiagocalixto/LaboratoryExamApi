package br.com.tiago.labs_and_exams.frameworkAndDrivers.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@Slf4j
public class ResponseApi {

    private final String status;
    private final String message;
    private final Object body;

    public static ResponseApi setResponseSuccess(String statusHttp, String message, Object body) {
        return ResponseApi.builder()
                .status(statusHttp)
                .message(message)
                .body(body).build();
    }

    public static ResponseApi setResponseSuccess(String statusHttp, String message) {
        return ResponseApi.builder()
                .status(statusHttp)
                .message(message)
                .body("").build();
    }
}
