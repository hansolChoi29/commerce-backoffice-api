package com.example.ledger.domain.purchaseorder.dto.command;

public class POFindOneCommand {
    private Long id;

    public POFindOneCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
