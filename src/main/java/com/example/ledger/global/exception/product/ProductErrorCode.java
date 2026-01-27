package com.example.ledger.global.exception.product;

import org.springframework.http.HttpStatus;

public enum ProductErrorCode {
    PRODUCT_NOT_FOUND(
            HttpStatus.NOT_FOUND.value(),
            404,
            "해당 상품을 찾을 수 없습니다. 상품 정보를 확인해 주세요."),
    PRODUCT_NAME_DUPLICATE(
            HttpStatus.CONFLICT.value(),
            409,
            "이미 등록된 상품입니다. 다른 상품명으로 입력해 주세요."),
    PRODUCT_INVALID_ID(
            HttpStatus.BAD_REQUEST.value(),
            400,
            "상품 ID는 1 이상의 값이어야 합니다. 입력값을 확인해 주세요."),
    PRODUCT_NO_STATUS(
            HttpStatus.BAD_REQUEST.value(),
            400,
            "삭제된 상품은 발주할 수 없습니다. 상품 상태를 확인해 주세요.");


    private final int status;
    private final int code;
    private final String message;

    ProductErrorCode(
            int status,
            int code,
            String message
    ) {
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
