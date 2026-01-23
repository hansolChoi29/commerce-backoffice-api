package com.example.ledger.domain.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    // 상품 : 발주/입고/매출/재고가 전부 이 상품을 기준으로 움직임
    // 그래서 해당 레포엔 상품 등록, 목록/검색, 상태변경 있어야 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
}
