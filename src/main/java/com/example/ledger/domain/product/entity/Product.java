package com.example.ledger.domain.product.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {
    // 상품 : 발주/입고/매출/재고가 전부 이 상품을 기준으로 움직임
    // 그래서 해당 레포엔 상품 등록, 목록/검색, 상태변경 있어야 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku", nullable = false)
    private String sku; // 상품코드(관리목적)

    @Column(name = "name", nullable = false, length = 255)
    private String name; // 상품명

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ProductStatus status; // 판매상태

    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice; // 판매 단가 (매출 시)

    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice; // 매입 원가 (발주 시)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
