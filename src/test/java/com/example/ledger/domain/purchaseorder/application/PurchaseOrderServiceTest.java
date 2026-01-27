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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

    // 거래처가 SUPPLIER + ACTIVE고 아이템 상품이 deleted=false+ACTIVE면
    // purchaseOrderRepository.save() 1회
    // purchaseOrderItemRepository.save() 아이템 개수만큼
    // 결과 POCreateResult가 saved 값 기반으로 반환

    // 시나리오
    // 1. 거래처 없음 - 예외, PO 저장도 하면 안 됨
    // 2. 거래처 type != SUPPLIER - 예외, PO 저장도 하면 안 됨
    // 3. 거래서 status != ACTIVE - 예외, PO 저장도 하면 안 됨
    // 4. 상품 없음(아이템 중 하나) - 예외
    // 5. 상품 deleted = true - 예외
    // 6. 상품 status != ACTIVE - 예외

    @Test
    @DisplayName("발주 생성")
    void purchaes_orders_success(){

    }


}