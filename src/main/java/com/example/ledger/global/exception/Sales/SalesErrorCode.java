package com.example.ledger.global.exception.Sales;

public enum SalesErrorCode {
    ;
    private final int status;
    private final int code;
    private final String message;

    SalesErrorCode(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
