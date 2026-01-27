package com.example.ledger.domain.purchaseorder.application;


import com.example.ledger.domain.partner.entity.Partner;
import com.example.ledger.domain.partner.entity.PartnerStatus;
import com.example.ledger.domain.partner.entity.PartnerType;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;
import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrder;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderItem;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderItemRepository;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final PartnerRepository partnerRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            ProductRepository productRepository,
            PurchaseOrderItemRepository purchaseOrderItemRepository,
            PartnerRepository partnerRepository
    ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.partnerRepository = partnerRepository;
    }

    @Transactional
    public POCreateResult create(POCreateCommand command) {
        Partner partner = partnerRepository.findById(command.getPartnerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 거래처입니다."));

        if (partner.getType() != PartnerType.SUPPLIER) {
            throw new IllegalArgumentException("공급처만 발주할 수 있습니다.");
        }
        if (partner.getStatus() != PartnerStatus.ACTIVE) {
            throw new IllegalArgumentException("비활성 거래처는 발주할 수 없습니다.");
        }

        String poNo = generatePoNo();
        PurchaseOrder saved = purchaseOrderRepository.save(
                PurchaseOrder.create(partner.getId(), poNo)
        );

        List<Item> items = command.getItems();
        for (Item item : items) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

            if (product.isDeleted()) {
                throw new IllegalArgumentException("삭제된 상품은 발주할 수 없습니다.");
            }
            if (product.getStatus() != ProductStatus.ACTIVE) {
                throw new IllegalArgumentException("비활성 상품은 발주할 수 없습니다.");
            }

            PurchaseOrderItem poItem = PurchaseOrderItem.create(
                    saved.getId(),
                    product.getId(),
                    item.getOrderQty(),
                    item.getUnitCost()
            );

            purchaseOrderItemRepository.save(poItem);
        }

        return new POCreateResult(
                saved.getId(),
                saved.getPoNo(),
                saved.getStatus(),
                saved.getOrderedAt()
        );
    }

    private String generatePoNo() {
        // 임시: 나중에
        return "PO-" + System.currentTimeMillis();
    }
}