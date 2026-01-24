package com.example.ledger.domain.product.application.sku;

import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RamdomSkuGenerator implements SkuGenerator {
    @Override
    public String generate() {
        // SKU- 12자리대소문자숫자 조합
        String random = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase();

        return "SKU-" + random;
    }
}
