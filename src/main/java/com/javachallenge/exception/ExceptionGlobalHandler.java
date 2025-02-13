package com.javachallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;


@ControllerAdvice
public class ExceptionGlobalHandler {
    private static final String PARAMETRO_EN_FORMATO_NO_VALIDO = "Parametro en formato no valido: ";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFoundException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> handleBadRequestException(BadRequest ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequestException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleBadRequestException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(PARAMETRO_EN_FORMATO_NO_VALIDO + ex.getName(), HttpStatus.BAD_REQUEST);
    }


}
