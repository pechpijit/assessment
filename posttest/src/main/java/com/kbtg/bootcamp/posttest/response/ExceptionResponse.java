package com.kbtg.bootcamp.posttest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class ExceptionResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private final HttpStatus httpStatus;

    private final ZonedDateTime dateTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ExceptionResponse(String message, HttpStatus httpStatus, ZonedDateTime dateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public ExceptionResponse(HttpStatus httpStatus, ZonedDateTime dateTime, List<String> errors) {
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public List<String> getErrors() {
        return errors;
    }
}
