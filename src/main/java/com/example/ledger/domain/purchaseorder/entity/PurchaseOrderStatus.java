package com.example.ledger.domain.purchaseorder.entity;

public enum PurchaseOrderStatus {
    REQUESTED, // 발주 요청을 등록한 상태 (아직 확정/승인 전)
    APPROVED, // 발주가 확정/승인된 상태 (공급처에 실제로 발주를 넣은 상태)
    CANCELLED, // 발주가 취소 됨 (진행 중단)
    RECEIVED // 발주 기 준으로 입고까지 완료된 상태
}
