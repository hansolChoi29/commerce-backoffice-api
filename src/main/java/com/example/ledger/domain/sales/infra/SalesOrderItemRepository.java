package com.example.ledger.domain.sales.infra;

import com.example.ledger.domain.sales.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, Long> {
}
