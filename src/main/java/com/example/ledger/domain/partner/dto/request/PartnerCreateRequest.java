package com.example.ledger.domain.partner.dto.request;

import com.example.ledger.domain.partner.entity.PartnerStatus;
import com.example.ledger.domain.partner.entity.PartnerType;

public class PartnerCreateRequest {
    private final String name;
    private final PartnerType type;

    public PartnerCreateRequest(String name, PartnerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PartnerType getType() {
        return type;
    }
}
