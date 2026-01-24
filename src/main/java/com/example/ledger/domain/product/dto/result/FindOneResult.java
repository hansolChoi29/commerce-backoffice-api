package com.example.ledger.domain.product.dto.result;

import com.example.ledger.domain.product.entity.ProductStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FindOneResult {
    private final Long id;
    private final String sku;
    private final String name;
    private final ProductStatus status;
    private final BigDecimal salePrice;
    private final BigDecimal costPrice;
    private final LocalDateTime createdAt;

    public FindOneResult(
            Long id,
            String sku,
            String name,
            ProductStatus status,
            BigDecimal salePrice,
            BigDecimal costPrice,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.status = status;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
