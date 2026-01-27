package com.example.ledger.domain.partner.application;


import com.example.ledger.domain.partner.dto.command.PartnerCreateCommand;
import com.example.ledger.domain.partner.dto.result.PartnerCreateResult;
import com.example.ledger.domain.partner.entity.Partner;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import com.example.ledger.global.exception.partner.PartnerErrorCode;
import com.example.ledger.global.exception.partner.PartnerException;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public PartnerCreateResult create(PartnerCreateCommand command) {
        if (partnerRepository.existsByName(command.getName())) {
            throw new PartnerException(PartnerErrorCode.PARTNER_NAME_DUPLICATE);
        }
        Partner partner = Partner.create(command.getName(), command.getType());
        Partner saved = partnerRepository.save(partner);


        return new PartnerCreateResult(
                saved.getId(),
                saved.getName(),
                saved.getType()
        );
    }
}
