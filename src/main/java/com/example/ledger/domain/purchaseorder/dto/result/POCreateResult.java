package com.example.ledger.domain.purchaseorder.dto.result;

import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;

public class POCreateResult {
    private final Long purchaseOrderId;
    private final String poNo;
    private final PurchaseOrderStatus status;
    private final LocalDateTime orderedAt;

    public POCreateResult(
            Long purchaseOrderId,
            String poNo,
            PurchaseOrderStatus status,
            LocalDateTime orderedAt
    ) {
        this.purchaseOrderId = purchaseOrderId;
        this.poNo = poNo;
        this.status = status;
        this.orderedAt = orderedAt;
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
