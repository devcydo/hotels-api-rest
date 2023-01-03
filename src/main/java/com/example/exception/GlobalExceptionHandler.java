package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.client.WebServiceClientException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourcenNotFound(ResourceNotFoundException ex) {
        String response = ex.getMessage();
        return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequest(BadRequestException ex) {
        String response = ex.getMessage();
        return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebServiceClientException.class)
    public ResponseEntity<String> connectionError(WebServiceClientException ex) {
        String response = "Microservice unavailable";
        return new ResponseEntity<String>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> serverError(Exception ex) {
        String response = ex.getMessage();
        return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
