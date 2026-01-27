package com.example.ledger.domain.purchaseorder.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    // 발주 문서 자체(공급처/상태/발주일 등)
    // 발주 한 건의 댛표 정보임
    // 그래서 해당 레포에는 저장과 조회가 들어가야함
    // 발주 생성/발주 상태 변경(요청에서 승인으로), 발주 목록 조회
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private Long id;

    @Column(name = "po_no")
    private String poNo; // 사람이 보는 발주 번호

    @Column(name = "partner_id", nullable = false)
    private Long partnerId; // TODO : 연관관계

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private PurchaseOrderStatus status;
    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    protected PurchaseOrder() {
    }

    public PurchaseOrder(Long id, String poNo, Long partnerId, PurchaseOrderStatus status, LocalDateTime orderedAt) {
        this.id = id;
        this.poNo = poNo;
        this.partnerId = partnerId;
        this.status = status;
        this.orderedAt = orderedAt;
    }

    public static PurchaseOrder create(
            Long partnerId,
            String poNo
    ) {
        PurchaseOrder order = new PurchaseOrder();
        order.partnerId = partnerId;
        order.poNo = poNo;
        order.status = PurchaseOrderStatus.REQUESTED;
        order.orderedAt = LocalDateTime.now();
        return order;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public String getPoNo() {
        return poNo;
    }

    public Long getId() {
        return id;
    }
}
