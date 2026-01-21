package com.example.ledger.global.exception;

public class Field {
    private final String name;
    private final String reason;

    public Field(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }
}
