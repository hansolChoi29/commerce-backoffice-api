package com.example.ledger.global.exception.partner;

import org.springframework.http.HttpStatus;

public enum PartnerErrorCode {
    PARTNER_NAME_DUPLICATE(
            HttpStatus.CONFLICT.value(),
            409,
            "이미 등록된 거래처명입니다. 다른 이름으로 입력해 주세요."
    ),
    PARTNER_NOT_FOUND(
            HttpStatus.NOT_FOUND.value(),
            404,
            "해당 거래처를 찾을 수 없습니다. 거래처 정보를 확인해 주세요."
    ),
    PARTNER_NO_TYPE(
            HttpStatus.BAD_REQUEST.value(),
            400,
            "공급처만 발주가 가능합니다. 거래처 유형을 확인해 주세요."
    ),
    PARTNER_NO_STATUS(
            HttpStatus.BAD_REQUEST.value(),
            400,
            "비활성 거래처는 발주가 불가합니다. 거래처 상태를 확인해 주세요."
    );


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
