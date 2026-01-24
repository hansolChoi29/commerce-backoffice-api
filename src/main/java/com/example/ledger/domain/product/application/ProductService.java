package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.application.sku.SkuGenerator;
import com.example.ledger.domain.product.dto.command.CreateCommand;
import com.example.ledger.domain.product.dto.result.CreateResult;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.infra.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SkuGenerator skuGenerator;

    public ProductService(
            ProductRepository productRepository,
            SkuGenerator skuGenerator
    ) {
        this.skuGenerator = skuGenerator;
        this.productRepository = productRepository;
    }

    public CreateResult create(CreateCommand command) {
        String sku = skuGenerator.generate();
        Product product = Product.create(
                sku,
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
