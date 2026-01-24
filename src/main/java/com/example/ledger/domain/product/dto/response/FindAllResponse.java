package com.example.ledger.domain.product.dto.response;

import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;

import java.math.BigDecimal;

public class FindAllResponse {
    private final Long id;
    private final String sku;
    private final String name;
    private final ProductStatus status;
    private final BigDecimal salePrice;
    private final BigDecimal costPrice;
    public FindAllResponse(
            Long id,
            String sku,
            String name,
            ProductStatus status,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
        this.id = id;
        this.sku=sku;
        this.name = name;
        this.status = status;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

    public String getSku() {
        return sku;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public static FindAllResponse from(Product product) {
        return new FindAllResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStatus(),
                product.getSalePrice(),
                product.getCostPrice());
    }

    public Long getId() {
        return id;
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
