package com.example.ledger.global.exception;


import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
                        e.getErrors()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validation(MethodArgumentNotValidException e) {
        List<Field> errors = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new Field(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest()
                .body(ApiResponse.fail(
                        "입력값이 올바르지 않습니다.",
                        1000, errors)
                );
    }
}
