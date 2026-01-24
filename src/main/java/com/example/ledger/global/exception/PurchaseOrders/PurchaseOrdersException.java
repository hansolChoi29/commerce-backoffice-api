package com.example.ledger.global.exception.PurchaseOrders;

import com.example.ledger.global.exception.ErrorException;
import com.example.ledger.global.exception.Field;

import java.util.List;

public class PurchaseOrdersException extends ErrorException {
    public PurchaseOrdersException(PurchaseOrdersErrorCode errorCode) {
        super(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                List.of()
        );
    }

    public PurchaseOrdersException(PurchaseOrdersErrorCode errorCode, List<Field> errors) {
        super(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                errors
        );
    }
}
