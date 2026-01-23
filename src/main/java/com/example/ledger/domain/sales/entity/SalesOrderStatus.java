package com.example.ledger.domain.sales.entity;

public enum SalesOrderStatus {
    CREATED, // 매출 문서 생성 (아직 확정 전)
    CONFIRMED, // 매출 확정 - 재고 차감/출고 처리 직결
    CANCELLED
}
