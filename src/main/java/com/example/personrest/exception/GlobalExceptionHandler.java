package com.example.personrest.exception;

import com.example.personrest.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse>  handleNullEntityReferenceException(NullEntityReferenceException e) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionResponse(e.getMessage()));
    }
}
