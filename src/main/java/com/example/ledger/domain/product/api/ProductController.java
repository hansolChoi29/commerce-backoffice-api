package com.example.ledger.domain.product.api;


import com.example.ledger.domain.product.application.ProductService;
import com.example.ledger.domain.product.dto.command.CreateCommand;
import com.example.ledger.domain.product.dto.command.FindOneCommand;
import com.example.ledger.domain.product.dto.request.CreateRequest;
import com.example.ledger.domain.product.dto.response.CreateResponse;
import com.example.ledger.domain.product.dto.response.FindOneResponse;
import com.example.ledger.domain.product.dto.result.CreateResult;
import com.example.ledger.domain.product.dto.result.FindOneResult;
import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateResponse>> create(
            @RequestBody CreateRequest request
    ) {
        // [문제점] : 만약, 클라이언트가 수정사항이 있다고 햇을 경우,
        // DTO만 수정하면 될 것을 컨트롤러단에서도 수정해야 하는 문제가 동반됨
        // TODO : DTO와 컨트롤러 다시 공부할 것

        CreateCommand command = new CreateCommand(
                request.getName(),
                request.getSalePrice(),
                request.getCostPrice()
        );
        CreateResult result = productService.create(command);
        CreateResponse response = new CreateResponse(
                result.getProductId(),
                result.getSku(),
                result.getName(),
                result.getSalePrice(),
                result.getCostPrice(),
                result.getCreatedAt()
        );
        return ResponseEntity.ok(ApiResponse.ok(
                "등록이 완료되었습니다.", response
        ));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<FindOneResponse>> findOne(
            @PathVariable Long id
    ) {
        FindOneCommand command = new FindOneCommand(id);
        FindOneResult result = productService.findOne(command);
        FindOneResponse response = new FindOneResponse(
                result.getId(),
                result.getSku(),
                result.getName(),
                result.getStatus(),
                result.getSalePrice(),
                result.getCostPrice(),
                result.getCreatedAt()
        );
        return ResponseEntity.ok(ApiResponse.ok("성공적으로 조회되었습니다.", response));
    }
}
