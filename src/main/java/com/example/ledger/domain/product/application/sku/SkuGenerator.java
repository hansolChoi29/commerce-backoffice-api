package com.example.ledger.domain.product.application.sku;

import org.springframework.stereotype.Component;

@Component
public interface SkuGenerator {
    String generate();
}
