package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.application.sku.SkuGenerator;
import com.example.ledger.domain.product.dto.command.CreateCommand;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.infra.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    SkuGenerator skuGenerator;
    @InjectMocks
    ProductService productService;
    // given 준비 (입력값 , mock 동작) / verify()
    // when 실제로 서비스 메서드 호출
    // assertThatTrowBy(()->...)
    // then 결과 검증 + mock이 어떻게 호출됐는지 검증 - assertThat

    @Test
    @DisplayName("상품 등록 성공")
    void create_success() {
        // givne
        CreateCommand command = new CreateCommand(
                "name",
                BigDecimal.valueOf(12000),
                BigDecimal.valueOf(13000)
        );
        given(productRepository.existsByName("name")).willReturn(false);
        given(skuGenerator.generate()).willReturn("SKU-012345678910");
        // willAnswer : 내가 직접 응답을 만들겠다
        // invocation : 이번 호출(호출 정보 묶음)
        given(productRepository.save(any(Product.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // when
        productService.create(command);
        //then
        verify(productRepository).existsByName("name");
        verify(skuGenerator).generate();
        verify(productRepository).save(any(Product.class));
    }
}