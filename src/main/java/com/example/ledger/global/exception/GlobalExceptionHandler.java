package com.example.ledger.global.exception;


import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ApiResponse> handler(
            ErrorException e
    ) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ApiResponse.fail(
                        e.getMessage(),
                        e.getCode(),
                        e.getErrors()
                ));
    }
}
