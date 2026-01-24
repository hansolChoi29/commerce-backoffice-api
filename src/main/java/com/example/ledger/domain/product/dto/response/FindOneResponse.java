package com.example.ledger.domain.product.dto.response;

import com.example.ledger.domain.product.entity.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public class FindOneResponse {
    private final String sku;
    private final String name;
    private final List<ProductStatus> status;
    private final int salePrice;
    private final int costPrice;
    private final LocalDateTime createdAt;

    public FindOneResponse(
            String sku,
            String name,
            List<ProductStatus> status,
            int salePrice,
            int costPrice,
            LocalDateTime createdAt
    ) {
        this.sku = sku;
        this.name = name;
        this.status = status;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.createdAt = createdAt;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public List<ProductStatus> getStatus() {
        return status;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
