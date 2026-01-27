package com.example.ledger.domain.purchaseorder.application.pono;

import org.springframework.stereotype.Component;

@Component
public class TimeBasedPoNoGenerator implements PoNoGenerator {
    @Override
    public String generate() {
        return "PO-" + System.currentTimeMillis();
    }
}
