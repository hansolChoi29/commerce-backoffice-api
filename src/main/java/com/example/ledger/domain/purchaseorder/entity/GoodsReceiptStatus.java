package com.example.ledger.domain.purchaseorder.entity;

public enum GoodsReceiptStatus {
    // 입고 문서 상태
    DRAFT, // 작성 중 : 임시 저장 상태  (검수/수량 확정 전)
    CONFIRMED, // 확정 : 입고 확정
    CANCELLED
}
