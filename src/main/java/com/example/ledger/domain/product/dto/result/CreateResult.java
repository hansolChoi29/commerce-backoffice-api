package com.example.ledger.domain.product.dto.result;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateResult {
    private final Long productId;
    private final String sku; // 상품 코드
    private final String name;
    private final BigDecimal salePrice;
    private final BigDecimal costPrice;
    private final LocalDateTime createdAt;

    public CreateResult(
            Long productId,
            String sku,
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice,
            LocalDateTime createdAt
    ) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getProductId() {
        return productId;
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
