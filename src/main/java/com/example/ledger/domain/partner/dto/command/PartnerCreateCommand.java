package com.example.ledger.domain.partner.dto.command;

import com.example.ledger.domain.partner.entity.PartnerType;

public class PartnerCreateCommand {
    private String name;
    private PartnerType type;

    public PartnerCreateCommand(String name, PartnerType type) {
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
