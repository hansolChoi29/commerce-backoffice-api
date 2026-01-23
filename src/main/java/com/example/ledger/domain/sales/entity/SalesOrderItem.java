package com.example.ledger.domain.sales.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sales_order_item")
public class SalesOrderItem {
    // 매출 문서에 포함된 판매 품목들
    // 어떤 상품을 몇 개 단가가 얼마가 여러 줄
    // 그래서 레포에는 매출 라인 저장/조회가 들어가야 함
    // 매출 상세 조회 시 라인 목록 가져오기, 매출 수정 시 라인 갱신

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_item_id")
    private Long id;
}
