package com.example.ledger.domain.purchaseorder.api;


import com.example.ledger.domain.purchaseorder.application.PurchaseOrderService;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.command.POFindOneCommand;
import com.example.ledger.domain.purchaseorder.dto.request.POCreateRequest;
import com.example.ledger.domain.purchaseorder.dto.response.POCreateResponse;
import com.example.ledger.domain.purchaseorder.dto.response.POFindOneResponse;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.domain.purchaseorder.dto.result.POFindOneResult;
import com.example.ledger.domain.purchaseorder.validation.PurchaseOrderValidation;
import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;
    private final PurchaseOrderValidation purchaseOrderValidation;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService,
            PurchaseOrderValidation purchaseOrderValidation
    ) {
        this.purchaseOrderValidation = purchaseOrderValidation;
        this.purchaseOrderService = purchaseOrderService;
    }

    // 발주 생성 REQUESTED - purchase-orders post
    // 발주 승인 APPROVED - purchase-orders patch - 멱등의 성질로
    // 발주 취소 CANCELLED - purchase-orders patch - 멱등의 성질로
    // 입고 완료 RECEIVED - purchase-orders patch - 멱등의 성질로

    @PostMapping
    public ResponseEntity<ApiResponse<POCreateResponse>> create(
            @RequestBody POCreateRequest request
    ) {
        purchaseOrderValidation.validate(request);

        POCreateCommand command = new POCreateCommand(
                request.getPartnerId(),
                request.getItems()
        );
        POCreateResult result = purchaseOrderService.create(command);
        POCreateResponse response = new POCreateResponse(
                result.getPurchaseOrderId(),
                result.getPoNo(),
                result.getStatus(),
                result.getOrderedAt()
        );

        return ResponseEntity.ok(ApiResponse.ok("발주 신청이 완료되었습니다", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<POFindOneResponse>> findOne(
            @PathVariable Long id
    ) {
        POFindOneCommand command = new POFindOneCommand(id);
        POFindOneResult result = purchaseOrderService.findOne(command);
        POFindOneResponse response = new POFindOneResponse(
                result.getPurhaseOrderId(),
                result.getPoNo(),
                result.getStatus(),
                result.getOrderedAt(),
                result.getItems()
        );
        return ResponseEntity.ok(ApiResponse.ok("발주서를 불러왔습니다.", response));
    }
}
