package com.example.ledger.global.exception.partner;

import com.example.ledger.global.exception.ErrorException;
import com.example.ledger.global.exception.Field;

import java.util.List;

public class PartnerException extends ErrorException {
    public PartnerException(PartnerErrorCode partnerErrorCdoe){
        super(
                partnerErrorCdoe.getStatus(),
                partnerErrorCdoe.getCode(),
                partnerErrorCdoe.getMessage(),
                List.of()
        );
    }

    public PartnerException(PartnerErrorCode partnerErrorCode, List<Field> errors){
        super(
                partnerErrorCode.getStatus(),
                partnerErrorCode.getCode(),
                partnerErrorCode.getMessage(),
                errors
        );
    }
}
