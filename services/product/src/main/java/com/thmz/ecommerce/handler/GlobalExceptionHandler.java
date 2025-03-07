package com.thmz.ecommerce.handler;

import com.thmz.ecommerce.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<ErrorResponse> handle(ProductPurchaseException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.ofMessage(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.ofMessage(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex){

        var errors = ex.getBindingResult()
                .getAllErrors().stream()
                .collect(
                        Collectors.toMap(error -> ((FieldError) error).getField(), ObjectError::getDefaultMessage)
                );


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.ofErrors(errors)
                );
    }


}
