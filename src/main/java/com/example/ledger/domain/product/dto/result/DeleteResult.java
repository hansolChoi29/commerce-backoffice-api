package com.example.ledger.domain.product.dto.result;

public class DeleteResult {
    private final Long id;

    public DeleteResult(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
