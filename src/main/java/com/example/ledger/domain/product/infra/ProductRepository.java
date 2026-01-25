package com.example.ledger.domain.product.infra;


import com.example.ledger.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable Pagealbe);

    // 수정 시 나 자신 제외하고 중복체크
    boolean existsByNameAndIdNot(String name, Long id);
}
