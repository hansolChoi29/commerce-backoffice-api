package com.example.ledger.domain.purchaseorder.entity;

public enum GoodsReceiptStatus {
    DRAFT, // 임시 저장 상태  (검수/수량 확정 전)
    CONFIRMED, // 입고 확정 
    CANCELLED
}
