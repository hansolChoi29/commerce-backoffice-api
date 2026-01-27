package com.example.ledger.domain.purchaseorder.dto.request;

import java.util.List;

public class POCreateRequest {
    private Long partnerId;
    private List<Item> items;

    public POCreateRequest(Long partnerId, List<Item> items) {
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
