package com.example.ledger.domain.purchaseorder.dto.request;

import java.util.List;

public class POCreateRequest {
    private Long productId;
    private List<Item> items;

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
