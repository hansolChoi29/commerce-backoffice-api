package com.example.ledger.domain.product.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateRequest {
    private final String sku; // 상품 코드
    private final String name;
    private final BigDecimal salePrice;
    private final BigDecimal costPrice;

    public CreateRequest(
            String sku,
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
        this.sku = sku;
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

    public String getSku() {
        return sku;
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
