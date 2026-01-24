package com.example.ledger.domain.product.dto.command;

import java.math.BigDecimal;

public class CreateCommand {
    private String sku; // 상품 코드
    private String name;
    private BigDecimal salePrice;
    private BigDecimal costPrice;

    public CreateCommand(
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
