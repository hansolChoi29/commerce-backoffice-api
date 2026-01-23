package com.example.ledger.domain.purchaseorder.infra;

import com.example.ledger.domain.purchaseorder.entity.GoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, Long> {
}
