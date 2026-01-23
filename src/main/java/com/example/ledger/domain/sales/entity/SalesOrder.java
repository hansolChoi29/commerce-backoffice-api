package com.example.ledger.domain.sales.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales_order")
public class SalesOrder {
    // 판매 / 매출 문서 한 건
    // 고객 / 상태 / 판매일 같은 대표정보를 가짐

    // 그래서 해당 레포에는 매출 문서 저장 / 조회가 들어가야 함
    // 매출 생성, 매출 확정, 매출 목록 / 상세 조회

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_id")
    private Long id;

    @Column(name = "so_no", nullable = false)
    private String soNo; // 사람이 보는 매출 번호

    @Column(name = "partner_id", nullable = false)
    private Long partnerId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;

    @Column(name = "soldAt", nullable = false)
    private LocalDateTime soldAt;
}
