package com.example.ledger.global.exception;

import java.util.List;

public class ErrorException extends RuntimeException {
    private final int status;
    private final int code;
    private final List<Field> errors;

    public ErrorException(
            int status,
            int code,
            String message,
            List<Field> errors
    ) {
        super(message);
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public List<Field> getErrors() {
        return errors;
    }
}
