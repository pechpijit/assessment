package com.kbtg.bootcamp.posttest.exception;

import com.kbtg.bootcamp.posttest.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> notValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        ExceptionResponse apiExceptionResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicationException.class})
    public ResponseEntity<ExceptionResponse> handleDuplicationException(DuplicationException duplicationException) {
        ExceptionResponse apiExceptionResponse = new ExceptionResponse(
                duplicationException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {
        ExceptionResponse apiExceptionResponse = new ExceptionResponse(
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InternalServiceException.class})
    public ResponseEntity<ExceptionResponse> handleInternalServiceException(InternalServiceException internalServiceException) {
        ExceptionResponse apiExceptionResponse = new ExceptionResponse(
                internalServiceException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
