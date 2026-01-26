package com.example.ledger.domain.purchaseorder.dto.request;

import java.util.List;

public class POCreateRequest {
    private final Long productId;
    private final List<Item> items;

    public POCreateRequest(Long productId, List<Item> items) {
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
