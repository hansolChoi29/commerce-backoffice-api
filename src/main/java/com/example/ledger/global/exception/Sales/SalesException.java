package com.example.ledger.global.exception.Sales;

import com.example.ledger.global.exception.ErrorException;
import com.example.ledger.global.exception.Field;

import java.util.List;

public class SalesException extends ErrorException {
    public SalesException(SalesErrorCode errorCode) {
        super(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                List.of()
        );
    }

    public SalesException(SalesErrorCode errorCode, List<Field> errors) {
        super(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                errors
        );
    }

}
