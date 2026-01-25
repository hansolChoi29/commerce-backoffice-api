package com.example.ledger.domain.product.dto.command;

public class DeleteCommand {
    private Long id;

    public DeleteCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
