package com.example.ledger.domain.purchaseorder.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "purchase_order_item")
public class PurchaseOrderItem {
    // 발주서 안에 들어가는 품목들
    // 어떤 상품을 몇 개, 단가 얼마를 여러 줄로 저장
    // 그래서 해당 레포에는 발주 품목 라인 저장/조회가 들어가야 함
    // 발주 상세 조회 시 라인들 가져오기, 발주 수정 시 라인 갱신
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_item_id")
    private Long id;

    @Column(name = "purchase_order_id", nullable = false)
    private Long purchaseOrderId; // TODO

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "order_qty", nullable = false)
    private int orderQty; //  발주 수량

    @Column(name = "unit_cost", nullable = false)
    private BigDecimal unitCost; // 발주 단가 (매입 단가)

    protected PurchaseOrderItem() {
    }

    public PurchaseOrderItem(Long id, Long purchaseOrderId, Long productId, int orderQty, BigDecimal unitCost) {
        this.id = id;
        this.purchaseOrderId = purchaseOrderId;
        this.productId = productId;
        this.orderQty = orderQty;
        this.unitCost = unitCost;
    }

    public static PurchaseOrderItem create(
            Long purchaseOrderId,
            Long productId,
            int orderQty,
            BigDecimal unitCost
    ) {
        PurchaseOrderItem item = new PurchaseOrderItem();
        item.purchaseOrderId = purchaseOrderId;
        item.productId = productId;
        item.orderQty = orderQty;
        item.unitCost = unitCost;
        return item;
    }

    public Long getId() {
        return id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }
}
