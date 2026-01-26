package com.example.ledger.domain.purchaseorder.dto.request;

import java.math.BigDecimal;

public class Item {
    private Long productId;
    private int orderQty;
    private BigDecimal unitCost;

    public Item(Long productId, int orderQty, BigDecimal unitCost) {
        this.productId = productId;
        this.orderQty = orderQty;
        this.unitCost = unitCost;
    }

    public Long getProductId() {
        return productId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }
}
