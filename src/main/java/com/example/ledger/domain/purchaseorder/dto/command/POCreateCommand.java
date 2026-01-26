package com.example.ledger.domain.purchaseorder.dto.command;


import com.example.ledger.domain.purchaseorder.dto.request.Item;

import java.util.List;

public class POCreateCommand {
    private final Long productId;
    private final List<Item> items;

    public POCreateCommand(Long productId, List<Item> items) {
        this.productId = productId;
        this.items = items;
    }

    public Long getProductId() {
        return productId;
    }

    public List<Item> getItems() {
        return items;
    }
}
