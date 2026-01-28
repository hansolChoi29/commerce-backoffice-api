package com.example.ledger.domain.purchaseorder.dto.response;

import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class POFindOneResponse {
    private final Long purchaseOrderId;
    private final String poNo;
    private final PurchaseOrderStatus status;
    private final LocalDateTime orderedAt;
    private final List<Item> items;

    public POFindOneResponse(
            Long purchaseOrderId,
            String poNo,
            PurchaseOrderStatus status,
            LocalDateTime orderedAt,
            List<Item> items
    ) {
        this.purchaseOrderId = purchaseOrderId;
        this.poNo = poNo;
        this.status = status;
        this.orderedAt = orderedAt;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public String getPoNo() {
        return poNo;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }
}
