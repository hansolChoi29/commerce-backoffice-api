package com.example.ledger.domain.purchaseorder.api;


import com.example.ledger.domain.purchaseorder.application.PurchaseOrderService;
import com.example.ledger.domain.purchaseorder.dto.command.POCreateCommand;
import com.example.ledger.domain.purchaseorder.dto.request.POCreateRequest;
import com.example.ledger.domain.purchaseorder.dto.response.POCreateResponse;
import com.example.ledger.domain.purchaseorder.dto.result.POCreateResult;
import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<POCreateResponse>> create(
            @RequestBody POCreateRequest request
    ) {
        POCreateCommand command = new POCreateCommand(
                request.getProductId(),
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
}
