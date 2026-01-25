package com.example.ledger.domain.product.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateResponse {
    private String name;
    private BigDecimal salePrice;
    private BigDecimal costPrice;

    public UpdateResponse(
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
