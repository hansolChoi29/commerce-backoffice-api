package com.example.ledger.domain.purchaseorder.application;


import com.example.ledger.domain.partner.entity.Partner;
import com.example.ledger.domain.partner.entity.PartnerStatus;
import com.example.ledger.domain.partner.entity.PartnerType;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;
import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.domain.purchaseorder.application.pono.PoNoGenerator;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrder;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderItem;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderItemRepository;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderRepository;
import com.example.ledger.global.exception.partner.PartnerErrorCode;
import com.example.ledger.global.exception.partner.PartnerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final PartnerRepository partnerRepository;
    private final PoNoGenerator poNoGenerator;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            ProductRepository productRepository,
            PurchaseOrderItemRepository purchaseOrderItemRepository,
            PartnerRepository partnerRepository,
            PoNoGenerator poNoGenerator
    ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.partnerRepository = partnerRepository;
        this.poNoGenerator = poNoGenerator;
    }

    @Transactional
    public POCreateResult create(POCreateCommand command) {
        Partner partner = partnerRepository.findById(command.getPartnerId())
                .orElseThrow(() -> new PartnerException(PartnerErrorCode.PARTNER_NOT_FOUND));

        if (partner.getType() != PartnerType.SUPPLIER) {
            throw new PartnerException(PartnerErrorCode.PARTNER_NO_TYPE);
        }
        if (partner.getStatus() != PartnerStatus.ACTIVE) {
            throw new PartnerException(PartnerErrorCode.PARTNER_NO_STATUS);
        }

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
        }
        // 발주 1건 저장 -> 발주 품목 라인 n 저장
        String poNo = poNoGenerator.generate();
        PurchaseOrder saved = purchaseOrderRepository.save(
                PurchaseOrder.create(partner.getId(), poNo)
        );

        for (Item item : items) {
            PurchaseOrderItem poItem = PurchaseOrderItem.create(
                    saved.getId(),
                    item.getProductId(),
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
}