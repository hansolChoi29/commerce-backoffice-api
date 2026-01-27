package com.example.ledger.domain.partner.dto.result;

import com.example.ledger.domain.partner.entity.PartnerType;

public class PartnerCreateResult {
    private Long partnerId;
    private String name;
    private PartnerType type;

    public PartnerCreateResult(Long partnerId, String name, PartnerType type) {
        this.partnerId = partnerId;
        this.name = name;
        this.type = type;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public String getName() {
        return name;
    }

    public PartnerType getType() {
        return type;
    }
}
