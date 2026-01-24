package com.example.ledger.domain.product.dto.command;

public class FindOneCommand {
    private final Long id;

    public FindOneCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
