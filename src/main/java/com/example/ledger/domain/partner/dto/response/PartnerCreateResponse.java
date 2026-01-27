package com.example.ledger.domain.partner.dto.response;

import com.example.ledger.domain.partner.entity.PartnerType;

public class PartnerCreateResponse {
    private Long partnerId;
    private String name;
    private PartnerType type;

    public PartnerCreateResponse(Long partnerId, String name, PartnerType type) {
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
