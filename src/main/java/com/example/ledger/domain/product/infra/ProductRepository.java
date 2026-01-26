package com.example.ledger.domain.product.infra;


import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Page<Product> findAllByDeletedFalseAndStatus(ProductStatus status, Pageable pageable);
    Optional<Product> findByIdAndDeletedFalse(Long id);

    boolean existsByNameAndDeletedFalse(String name);
}
