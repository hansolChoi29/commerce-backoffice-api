package com.example.ledger.domain.inventory.infra;

import com.example.ledger.domain.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
