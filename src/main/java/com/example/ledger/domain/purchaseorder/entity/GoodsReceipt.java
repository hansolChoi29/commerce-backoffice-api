package com.example.ledger.domain.purchaseorder.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "goods_receipt")
@Entity
public class GoodsReceipt {
    // 실제로 물건들이 들어왔다는 입고 문서
    // TODO: 발주와 1:1 or 1:N으로 할건데 추후
    // 그래서 해당 레포에는 입고문서 저장/조회가 들어가야 함
    // 입고 확정 처리, 입고 이력 조회
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_receipt_id")
    private Long id;

    @Column(name = "gr_no", nullable = false)
    private String grNo;

    @Column(name = "purchase_order_id", nullable = false)
    private Long purchaseOrderId; // TODO

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GoodsReceiptStatus status;

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt;
}
