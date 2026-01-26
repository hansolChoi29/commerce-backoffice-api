package com.example.ledger.domain.purchaseorder.application;


import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderItemRepository;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            ProductRepository productRepository,
            PurchaseOrderItemRepository purchaseOrderItemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
    }

    public POCreateResult create(POCreateCommand command) {
        return new POCreateResult(null, null, null, null);
    }
}
