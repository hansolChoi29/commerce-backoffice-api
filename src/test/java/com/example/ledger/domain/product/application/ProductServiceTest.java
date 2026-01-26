package com.example.ledger.domain.product.application;

import com.example.ledger.domain.product.application.sku.SkuGenerator;
import com.example.ledger.domain.product.dto.command.*;
import com.example.ledger.domain.product.dto.response.FindAllResponse;
import com.example.ledger.domain.product.dto.response.FindOneResponse;
import com.example.ledger.domain.product.dto.result.FindOneResult;
import com.example.ledger.domain.product.dto.result.UpdateResult;
import com.example.ledger.domain.product.entity.Product;
import com.example.ledger.domain.product.entity.ProductStatus;
import com.example.ledger.domain.product.infra.ProductRepository;
import com.example.ledger.global.exception.product.ProductException;
import com.example.ledger.global.response.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.ledger.domain.product.entity.ProductStatus.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        given(productRepository.existsByNameAndDeletedFalse("name")).willReturn(false);
        given(skuGenerator.generate()).willReturn("SKU-012345678910");
        given(productRepository.save(any(Product.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // when
        productService.create(command);
        //then
        verify(productRepository).existsByNameAndDeletedFalse("name");
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
        given(productRepository.existsByNameAndDeletedFalse("name")).willReturn(false); // success
        given(productRepository.existsByNameAndDeletedFalse("name")).willReturn(true);  // duplicate

        // [핵심]이 예외는 터져야 한다
        assertThrows(
                ProductException.class,
                () -> productService.create(command)
        );

        verify(productRepository).existsByNameAndDeletedFalse("name");
        verify(productRepository, never()).save(any(Product.class));
        verify(skuGenerator, never()).generate(); // 호출되면 안 된다
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("단건 조회 성공")
    void findOne_success() {
        Long id = 1L;
        Product product = new Product(
                id,
                "SKU-01234567891",
                "name",
                ACTIVE,
                BigDecimal.valueOf(12000),
                BigDecimal.valueOf(13000),
                LocalDateTime.now()
        );

        given(productRepository.findByIdAndDeletedFalse(id)).willReturn(Optional.of(product));

        FindOneResult result = productService.findOne(new FindOneCommand(id));

        assertThat(result.getId()).isEqualTo(id);
        verify(productRepository).findByIdAndDeletedFalse(id);
    }

    @Test
    @DisplayName("존재하지 않는 Id")
    void findOne_Id_isNull() {
        Long id = 1L;
        given(productRepository.findByIdAndDeletedFalse(id)).willReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findOne(new FindOneCommand(id)))
                .isInstanceOf(ProductException.class)
                .hasMessageContaining("존재하지 않는 상품");

        verify(productRepository).findByIdAndDeletedFalse(id);
    }

    @Test
    @DisplayName("상품  전체조회 성공")
    void findAll_success() {
        Product p1 = Product.create("SKU-1", "name1", BigDecimal.valueOf(1200), BigDecimal.valueOf(12000));
        Product p2 = Product.create("SKU-2", "name2", BigDecimal.valueOf(4200), BigDecimal.valueOf(92000));

        Page<Product> productPage = new PageImpl<>(
                List.of(p1, p2),
                PageRequest.of(0, 20),
                22
        );

        given(productRepository.findAllByDeletedFalseAndStatus(eq(ACTIVE), any(Pageable.class)))
                .willReturn(productPage);

        FindAllCommand command = new FindAllCommand(PageRequest.of(0, 20));

        PageResponse<FindAllResponse> result = productService.findAll(command);

        // 해당 페이지 담긴 목록 아아템 2개여야 한다
        assertThat(result.getItem()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(22);
        assertThat(result.isHasNext()).isTrue();
    }

    @Test
    @DisplayName("상품 수정 성공")
    void product_put_success() {
        Long id = 1L;
        Product product = Product.create(
                "SKU-TEST",
                "oldName",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(900)
        );
        given(productRepository.findById(id))
                .willReturn(Optional.of(product));

        UpdateCommand command = new UpdateCommand(
                "name",
                BigDecimal.valueOf(5000),
                BigDecimal.valueOf(4000)
        );
        UpdateResult result = productService.update(id, command);
        verify(productRepository).findById(id);

        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getSalePrice()).isEqualTo(BigDecimal.valueOf(5000));
        assertThat(result.getCostPrice()).isEqualTo(BigDecimal.valueOf(4000));

        // entity 변경 확인
        assertThat(product.getName()).isEqualTo("name");
        assertThat(product.getSalePrice()).isEqualTo(BigDecimal.valueOf(5000));
    }

    /*
     * 1) 삭제 후 목록에서 제외된다
     * given 상품 1생성
     * when delete
     * then findAllByDeletedFalseAndStatus 결과
     *
     * 2) 삭제 후 단건조회에서 제외된다
     * given 상품 1개 생성
     * when delete
     * then findByIdAndDeletedFalse로 조회 시 empty
     *
     * 3) 이름 중복 체크는 삭제된 상품은 제외한다
     * given name = a 생성 후 delete
     * when name = a 로 다시 생성 요청
     * then 성공
     *
     * 4) sku 유니크가 살아이쓴 것만 적용된다
     * given sku = aaa 로 상품 저장 후 delete = true로 변경
     * when sku = aaa + deleted = false로 새 상품 저장
     * then 성공
     * */
    @Test
    @DisplayName("삭제 후 목록에서 제외")
    void findAll_excludes_deleted_product() {
        FindAllCommand command = new FindAllCommand(PageRequest.of(0, 20));
        given(productRepository.findAllByDeletedFalseAndStatus(eq(ACTIVE), any(Pageable.class)))
                .willReturn(Page.empty());

        PageResponse<FindAllResponse> result = productService.findAll(command);

        assertThat(result.getItem()).isEmpty();
        verify(productRepository).findAllByDeletedFalseAndStatus(eq(ACTIVE), any(Pageable.class));
    }

    @Test
    @DisplayName("삭제 후 단건조회에서 제외")
    void findOne_excludes_deleted_product() {
        FindOneCommand command = new FindOneCommand(1L);
        given(productRepository.findByIdAndDeletedFalse(eq(1L))).willReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findOne(new FindOneCommand(1L)))
                .isInstanceOf(ProductException.class);
        verify(productRepository).findByIdAndDeletedFalse(eq(1L));
    }

    @Test
    @DisplayName("이름 중복 체크는 삭제된 상품은 제외")
    void name_duplicate_when_isDeleted() {
        CreateCommand command = new CreateCommand("name", BigDecimal.valueOf(12), BigDecimal.valueOf(12));

        given(productRepository.existsByNameAndDeletedFalse("name")).willReturn(false);
        given(skuGenerator.generate()).willReturn("SKU-NEW");
        given(productRepository.save(any(Product.class))).willAnswer(invocation -> invocation.getArgument(0));

        productService.create(command);

        verify(productRepository).existsByNameAndDeletedFalse("name");
        verify(skuGenerator).generate();
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("sku 유니크가 살아이쓴 것만 적용")
    void sku_unique_delete_true(){
        Product old = Product.create("SKU-AAA", "old", BigDecimal.TEN, BigDecimal.ONE);
        productRepository.saveAndFlush(old);

        old.delete();
        productRepository.flush();

        Product newer = Product.create("SKU-AAA", "new", BigDecimal.TEN, BigDecimal.ONE);

        productRepository.saveAndFlush(newer);
    }

}