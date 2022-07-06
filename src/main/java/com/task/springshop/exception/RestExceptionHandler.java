package com.task.springshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception, WebRequest request) {
        List<String> messages = exception.getValidationMessages();
        messages.forEach(log::error);

        return new ResponseEntity<>(new ErrorResponse(messages), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception, WebRequest request) {
        String message = exception.getMessage();
        log.error(message);

        return new ResponseEntity<>(new ErrorResponse(List.of(message)), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
