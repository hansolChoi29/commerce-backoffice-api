package com.example.ledger.global.exception.product;

import com.example.ledger.global.exception.ErrorException;
import com.example.ledger.global.exception.Field;

import java.util.List;

public class ProductException extends ErrorException {
    public ProductException(ProductErrorCode productErrorCode) {
        super(
                productErrorCode.getStatus(),
                productErrorCode.getCode(),
                productErrorCode.getMessage(),
                List.of()
        );
    }

    public ProductException(ProductErrorCode productErrorCode, List<Field> errors) {
        super(
                productErrorCode.getStatus(),
                productErrorCode.getCode(),
                productErrorCode.getMessage(),
                errors
        );
    }
}
