package com.example.ledger.domain.purchaseorder.dto.command;


import com.example.ledger.domain.purchaseorder.dto.request.Item;

import java.util.List;

public class POCreateCommand {
    private final Long partnerId;
    private final List<Item> items;

    public POCreateCommand(Long partnerId, List<Item> items) {
        this.partnerId = partnerId;
        this.items = items;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public List<Item> getItems() {
        return items;
    }
}
