package com.example.ledger.domain.sales.entity;

public enum SalesOrderStatus {
    // 매출 문서 상태
    CREATED, // 생성됨 : 매출 문서 생성 (아직 확정 전)
    CONFIRMED, // 확정 : 매출 확정 - 재고 차감/출고 처리 직결
    CANCELLED
}
