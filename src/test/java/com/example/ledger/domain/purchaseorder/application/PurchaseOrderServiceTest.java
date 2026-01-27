package com.example.ledger.domain.purchaseorder.application;

import com.example.ledger.domain.partner.entity.Partner;
import com.example.ledger.domain.partner.entity.PartnerStatus;
import com.example.ledger.domain.partner.entity.PartnerType;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.request.Item;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrder;
import com.example.ledger.domain.purchaseorder.entity.PurchaseOrderItem;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderItemRepository;
import com.example.ledger.domain.purchaseorder.infra.PurchaseOrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.ledger.domain.product.entity.ProductStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class PurchaseOrderServiceTest {
    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    ProductRepository productRepository;
    @Mock
    PartnerRepository partnerRepository;

    @Mock
    PurchaseOrderItemRepository purchaseOrderItemRepository;

    @InjectMocks
    PurchaseOrderService purchaseOrderService;

//    @Test
//    @DisplayName("발주서생성")
//    void purchaseOrder_success() {
//        POCreateCommand command = new POCreateCommand(
//                10L,
//                java.util.List.of(
//                        new Item(1L, 10, new java.math.BigDecimal("5500"))
//                )
//        );
//        // partner 잇다고 치고
//        Partner partner = new Partner(
//                10L,
//                PartnerType.SUPPLIER,
//                "name",
//                PartnerStatus.ACTIVE,
//                LocalDateTime.now()
//        );
//        // product statuc ACTIVE 라고 치고
//        Product product = new Product(
//                1L,
//                "SKU-01234567891",
//                "name",
//                ACTIVE,
//                BigDecimal.valueOf(12000),
//                BigDecimal.valueOf(13000),
//                LocalDateTime.now()
//        );
//        given(partnerRepository.findById(10L)).willReturn(Optional.of(partner));
//        given(productRepository.findById(1L)).willReturn(Optional.of(product));
//        PurchaseOrder saved = new PurchaseOrder();
//
//        given(purchaseOrderRepository.save(any(PurchaseOrder.class))).willReturn(saved);
//        POCreateResult result = purchaseOrderService.create(command);
//        assertThat(result);
//        then(purchaseOrderRepository).should().save(any(PurchaseOrder.class));
//        then(purchaseOrderItemRepository).should().save(any(PurchaseOrderItem.class));
//    }

}