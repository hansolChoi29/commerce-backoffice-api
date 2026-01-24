package com.example.ledger.domain.product.infra;


import com.example.ledger.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
}
