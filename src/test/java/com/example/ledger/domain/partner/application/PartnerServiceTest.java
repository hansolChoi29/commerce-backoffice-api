package com.example.ledger.domain.partner.application;

import com.example.ledger.domain.partner.dto.command.PartnerCreateCommand;
import com.example.ledger.domain.partner.entity.Partner;
import com.example.ledger.domain.partner.entity.PartnerType;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {
    @Mock
    PartnerRepository partnerRepository;
    @InjectMocks
    PartnerService partnerService;

    @Test
    @DisplayName("거래처 생성")
    void partner_create_success() {
        PartnerCreateCommand command = new PartnerCreateCommand("name", PartnerType.SUPPLIER);

        given(partnerRepository.existsByName("name")).willReturn(false);

        given(partnerRepository.save(any(Partner.class))).willAnswer(invocation -> invocation.getArgument(0));

        partnerService.create(command);

        verify(partnerRepository).existsByName("name");
        verify(partnerRepository).save(any(Partner.class));
    }
}