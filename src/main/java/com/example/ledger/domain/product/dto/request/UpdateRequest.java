package com.example.ledger.domain.product.dto.request;

import java.math.BigDecimal;

public class UpdateRequest {
    private final String name;
    private final BigDecimal salePrice;
    private final BigDecimal costPrice;

    public UpdateRequest(
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
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
