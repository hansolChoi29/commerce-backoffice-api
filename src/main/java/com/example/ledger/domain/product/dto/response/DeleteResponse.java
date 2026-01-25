package com.example.ledger.domain.product.dto.response;

import com.example.ledger.domain.product.entity.ProductStatus;

public class DeleteResponse {
    private final ProductStatus status;

    public DeleteResponse(ProductStatus status) {
        this.status = status;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
