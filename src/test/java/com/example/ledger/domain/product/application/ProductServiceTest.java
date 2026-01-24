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
import static org.mockito.Mockito.never;
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

    @Test
    @DisplayName("이미 존재하는 예외, sku/save 모두 금지")
    void create_name_duplicate() throws Exception {
        // 실패 시나리오 1
        // 이미 존재하는 상품이면 예외 + sku 생성도 / 저장도 하면 안 됨

        // 1) 실패 조건
        // 2) 실패 조건을 만족시키려면 mock을 어떻게 만들어야 하는가?
        // 3) 실패했으면 절대 실행되면 안 되는 다음 단계는 무엇인가?

        CreateCommand command = new CreateCommand(
                "name",
                BigDecimal.valueOf(12000),
                BigDecimal.valueOf(13000)
        );
        given(productRepository.existsByName("name")).willReturn(true);

        // [핵심]이 예외는 터져야 한다
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.create(command)
        );

        verify(productRepository).existsByName("name");
        verify(skuGenerator, never()).generate(); // 호출되면 안 된다
        verify(productRepository, never()).save(any(Product.class));
    }
}