package com.example.ledger.domain.purchaseorder.infra;

import com.example.ledger.domain.purchaseorder.entity.GoodsReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsReceiptItemRepository extends JpaRepository<GoodsReceiptItem, Long> {
}
