package com.example.ledger.domain.product.dto.command;


import org.springframework.data.domain.Pageable;

public class FindAllCommand {
   private final Pageable pageable;

    public FindAllCommand(Pageable pageable) {
        this.pageable = pageable;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
