package com.example.ledger.domain.purchaseorder.dto.result;

import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class POFindOneResult {
    private final Long purhaseOrderId;
    private final String poNo;
    private final PurchaseOrderStatus status;
    private final LocalDateTime orderedAt;
    private final List<Item> items;


    public POFindOneResult(
            Long purhaseOrderId,
            String poNo,
            PurchaseOrderStatus status,
            LocalDateTime orderedAt,
            List<Item> items
    ) {
        this.purhaseOrderId = purhaseOrderId;
        this.poNo = poNo;
        this.status = status;
        this.orderedAt = orderedAt;
        this.items = items;
    }

    public Long getPurhaseOrderId() {
        return purhaseOrderId;
    }

    public String getPoNo() {
        return poNo;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public List<Item> getItems() {
        return items;
    }
}
