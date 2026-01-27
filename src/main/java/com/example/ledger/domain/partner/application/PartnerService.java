package com.example.ledger.domain.partner.application;


import com.example.ledger.domain.partner.dto.command.PartnerCreateCommand;
import com.example.ledger.domain.partner.dto.result.PartnerCreateResult;
import com.example.ledger.domain.partner.infra.PartnerRepository;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public PartnerCreateResult create(PartnerCreateCommand command){


        return new PartnerCreateResult();
    }
}
