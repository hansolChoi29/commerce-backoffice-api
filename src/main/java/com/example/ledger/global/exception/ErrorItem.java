package com.example.ledger.global.exception;

import java.util.List;

public class ErrorItem {
    private final String code;
    private final List<Field> errors;

    public ErrorItem(String code, List<Field> errors) {
        this.code = code;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public List<Field> getErrors() {
        return errors;
    }
}
