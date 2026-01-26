package com.example.ledger.domain.purchaseorder.dto.response;

import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;

public class POCreateResponse {
    private final Long purchaseOrderId;
    private final String poNo;
    private final PurchaseOrderStatus status;
    private final LocalDateTime orderedAt;

    public POCreateResponse(
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

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
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
}
