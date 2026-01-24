package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.application.sku.SkuGenerator;
import com.example.ledger.domain.product.dto.command.CreateCommand;
import com.example.ledger.domain.product.dto.command.FindOneCommand;
import com.example.ledger.domain.product.dto.result.CreateResult;
import com.example.ledger.domain.product.dto.result.FindOneResult;
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
        if(productRepository.existsByName(command.getName())){
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }

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

    public FindOneResult findOne(FindOneCommand command){
        Product product = productRepository.findById(command.getId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new FindOneResult(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStatus(),
                product.getSalePrice(),
                product.getCostPrice(),
                product.getCreatedAt()
        );
    }
}
