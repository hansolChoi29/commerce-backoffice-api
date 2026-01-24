package com.example.ledger.global.exception;

import java.util.List;

public class ErrorItem {
    private final int code;
    private final List<Field> errors;

    public ErrorItem(int code, List<Field> errors) {
        this.code = code;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public List<Field> getErrors() {
        return errors;
    }
}
