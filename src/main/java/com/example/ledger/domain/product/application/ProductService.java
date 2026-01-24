package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.dto.command.CreateCommand;
import com.example.ledger.domain.product.dto.result.CreateResult;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.infra.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CreateResult create(CreateCommand command) {
        if (productRepository.existsBySku(command.getSku())) {
            throw new IllegalArgumentException("이미 있는 상품입니다.");
        }
        Product product = Product.create(
                command.getSku(),
                command.getName(),
                command.getSalePrice(),
                command.getCostPrice()
        );
        Product saved = productRepository.save(product);

        return new CreateResult(
                saved.getId(),
                saved.getSku(),
                saved.getName(),
                saved.getSalePrice(),
                saved.getCostPrice(),
                saved.getCreatedAt()
        );
    }
}
