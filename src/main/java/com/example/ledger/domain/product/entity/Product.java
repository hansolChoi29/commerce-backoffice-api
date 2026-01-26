package com.example.ledger.domain.product.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku", unique = true, nullable = false, length = 30)
    private String sku; // 상품코드(관리목적) - 랜덤하게

    @Column(name = "name", nullable = false, length = 255)
    private String name; // 상품명

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private ProductStatus status; // 판매상태

    @Column(name = "sale_price", nullable = false)
    private BigDecimal salePrice; // 판매 단가 (매출 시)

    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice; // 매입 원가 (발주 시)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    protected Product() {
    }

    public Product(
            Long id,
            String sku,
            String name,
            ProductStatus status,
            BigDecimal salePrice,
            BigDecimal costPrice,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.status = status;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.createdAt = createdAt;
    }

    public static Product create(
            String sku,
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
        Product product = new Product();
        product.sku = sku;
        product.name = name;
        product.salePrice = salePrice;
        product.costPrice = costPrice;
        product.status = ProductStatus.ACTIVE;
        product.createdAt = LocalDateTime.now();
        return product;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    // TODO : PrePersist 학습하여 적용하기

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void update(
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

/*
    [문제]
     소프트 삭제(행을 지우지 않음) 구조에서 sku에 unique가 걸려 있으면,
     삭제된 상품도 DB에 남아 있어서 동일 sku 재등록이 "중복"으로 실패할 수 있음.

     [해결책] deleted 컬럼 추가하여 유니크(sku, deleted) 묶기

     [설계] deleted = false 살아있는 제품
     (AAA-111, false) 1개만 존재 가능 - 중복 생성 방지
     (AAA-111, true)가 있어도 새로 (AAA-111, false)를 만들 수 있음 - 재등록 가능
*/

    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
