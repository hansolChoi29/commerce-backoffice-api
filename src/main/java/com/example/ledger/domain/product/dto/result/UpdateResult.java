package com.example.ledger.domain.product.dto.result;

import java.math.BigDecimal;

public class UpdateResult {
    private String name;
    private BigDecimal salePrice;
    private BigDecimal costPrice;

    public UpdateResult(String name, BigDecimal salePrice, BigDecimal costPrice) {
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }
}
