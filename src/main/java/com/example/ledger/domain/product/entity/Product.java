package com.example.ledger.domain.product.entity;

import com.example.ledger.domain.product.dto.command.UpdateCommand;
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

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // TODO : PrePersist 학습하여 적용하기

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

    public void update(
            String name,
            BigDecimal salePrice,
            BigDecimal costPrice
    ) {
        this.name = name;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }


    public void delete() {
        this.status = ProductStatus.INACTIVE;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
