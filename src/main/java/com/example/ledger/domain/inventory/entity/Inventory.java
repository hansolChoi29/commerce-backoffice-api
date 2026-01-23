package com.example.ledger.domain.inventory.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "inventory")
public class Inventory {
    // 현재 재고
    // 보통 상품 1개당 재고 1행 product_id 기준 유니크로 운영
    // 그래서 해당 레포에는 상품의 재고 조회/갱신이 되어야 함
    // 예를 들어 판매 시 재고 차감, 입고 시 재고 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;
}

