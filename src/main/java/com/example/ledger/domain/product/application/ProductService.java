package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.application.sku.SkuGenerator;
import com.example.ledger.domain.product.dto.command.*;
import com.example.ledger.domain.product.dto.response.FindAllResponse;
import com.example.ledger.domain.product.dto.result.CreateResult;
import com.example.ledger.domain.product.dto.result.FindOneResult;
import com.example.ledger.domain.product.dto.result.UpdateResult;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;
import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.global.exception.product.ProductErrorCode;
import com.example.ledger.global.exception.product.ProductException;
import com.example.ledger.global.response.PageResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
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
        if (productRepository.existsByNameAndDeletedFalse(command.getName())) {
            throw new ProductException(ProductErrorCode.PRODUCT_NAME_DUPLICATE);
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

    public FindOneResult findOne(FindOneCommand command) {
        Product product = productRepository.findByIdAndDeletedFalse(command.getId())
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

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

    public PageResponse<FindAllResponse> findAll(FindAllCommand command) {
        Page<FindAllResponse> page = productRepository
                .findAllByDeletedFalseAndStatus(ProductStatus.ACTIVE, command.getPageable())
                .map(FindAllResponse::from);

        return PageResponse.from(page);
    }

    // 수정은 멱등하게 설계할 것
    @Transactional
    public UpdateResult update(Long id, UpdateCommand command) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        product.update(
                command.getName(),
                command.getSalePrice(),
                command.getCostPrice()
        );

        return new UpdateResult(
                product.getName(),
                product.getSalePrice(),
                product.getCostPrice()
        );
    }
    @Transactional
    public void delete(DeleteCommand command) {
        Product product = productRepository.findByIdAndDeletedFalse(
                        command.getId())
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));
        product.delete();
    }
}
