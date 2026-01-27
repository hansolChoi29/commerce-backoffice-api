package com.example.ledger.domain.partner.api;


import com.example.ledger.domain.partner.application.PartnerService;
import com.example.ledger.domain.partner.dto.command.PartnerCreateCommand;
import com.example.ledger.domain.partner.dto.request.PartnerCreateRequest;
import com.example.ledger.domain.partner.dto.response.PartnerCreateResponse;
import com.example.ledger.domain.partner.dto.result.PartnerCreateResult;
import com.example.ledger.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/partner")
@RestController
public class PartnerController {
    public final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PartnerCreateResponse>> create(
            @RequestBody PartnerCreateRequest request
    ) {
        PartnerCreateCommand command = new PartnerCreateCommand(
                request.getName(),
                request.getType()
        );
        PartnerCreateResult result = partnerService.create(command);
        PartnerCreateResponse response = new PartnerCreateResponse(
                result.getPartnerId(),
                result.getName(),
                result.getType()
        );
        return ResponseEntity.ok(ApiResponse.ok(
                "거래처가 등록되었습니다.",
                response
        ));
    }
}
