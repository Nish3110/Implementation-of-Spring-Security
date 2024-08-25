package com.example.SpringBoot.SpringSecurity.advices;

import com.example.SpringBoot.SpringSecurity.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ApiError apiError = new ApiError(resourceNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new  ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
