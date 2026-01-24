package com.example.ledger.domain.purchaseorder.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "goods_receipt_item")
public class GoodsReceiptItem {
    // 입고 문서의 품목 줄
    // 어떤 상품이 몇 개 들어왔는지가 여기 기록된다
    // 이 라인이 기준이 되어 인벤토리가 증가됨
    // 그래서 해당 레포에는 입고 저장/조회가 들어가야 함
    // 입고 상세조회 시 라인 가져오기, 입고 확정 시 라인 기준으로 재고 반영
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_receipt_item_id")
    private Long id;

    @Column(name = "goods_receipt_id", nullable = false)
    private Long goodsReceiptId; // TODO

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "received_qty", nullable = false)
    private Long receivedQty; // 실제 입고 수량

    @Column(name = "unit_cost", nullable = false)
    private BigDecimal unitcost; // 발주 단가 (매입 단가)
}
