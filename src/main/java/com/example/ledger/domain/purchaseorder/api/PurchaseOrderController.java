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

    // 발주 생성 REQUESTED - purchase-orders post
    // 발주 승인 APPROVED - purchase-orders patch - 멱등의 성질로
    // 발주 취소 CANCELLED - purchase-orders patch - 멱등의 성질로
    // 입고 완료 RECEIVED - purchase-orders patch - 멱등의 성질로

    @PostMapping
    public ResponseEntity<ApiResponse<POCreateResponse>> create(
            @RequestBody POCreateRequest request
    ) {
         /*
            partnerId가 null인지
            items가 null/빈 리스트인지
            각 item에
            productId null인지
            orderQty <= 0 인지
            unitCost < 0 인지(또는 null이면 허용할지 정책)
        */
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
}
