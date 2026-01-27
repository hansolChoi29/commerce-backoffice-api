package com.example.ledger.domain.purchaseorder.validation;

import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.dto.request.POCreateRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PurchaseOrderValidation {
    // TODO : GlobalException ErrorCode add
    public void validate(POCreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("요청이 비어있습니다.");
        }
        if (request.getPartnerId() == null) {
            throw new IllegalArgumentException("partnerId는 필수입니다.");
        }

        List<Item> items = request.getItems();
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("발주 품목(items)은 최소 1개 이상이어야 합니다.");
        }

        for (Item item : items) {
            if (item.getProductId() == null) {
                throw new IllegalArgumentException("productId는 필수입니다.");
            }
            if (item.getOrderQty() <= 0) {
                throw new IllegalArgumentException("orderQty는 1 이상이어야 합니다.");
            }

            BigDecimal unitCost = item.getUnitCost();
            if (unitCost == null) {
                throw new IllegalArgumentException("unitCost는 필수입니다.");
            }
            if (unitCost.signum() <= 0) {
                throw new IllegalArgumentException("unitCost는 0보다 커야 합니다.");
            }
        }
    }
}
