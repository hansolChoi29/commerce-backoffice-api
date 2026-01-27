package com.example.ledger.global.exception.partner;

import org.springframework.http.HttpStatus;

public enum PartnerErrorCode {
    PARTNER_NAME_DUPLICATE(HttpStatus.CONFLICT.value(), 409, "이미 존재하는 이름입니다.");

    private final int status;
    private final int code;
    private final String message;

    PartnerErrorCode(int status, int code, String message) {
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
