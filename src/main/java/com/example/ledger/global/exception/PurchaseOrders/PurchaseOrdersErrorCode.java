package com.example.ledger.global.exception.PurchaseOrders;

import org.springframework.http.HttpStatus;

public enum PurchaseOrdersErrorCode {
    // service
    PURCHASEORDER_ID_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 404, "존재하지 않는 발주입니다."),
    PRODUCT_NAME_DUPLICATE(HttpStatus.CONFLICT.value(), 409, "이미 존재하는 발주입니다."),
    // controller
    PRODUCT_INVALID_ID(HttpStatus.CONFLICT.value(), 400, "id는 1 이상의 값이어야 합니다.");

    ;
    private final int status;
    private final int code;
    private final String message;

    PurchaseOrdersErrorCode(int status, int code, String message) {
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
