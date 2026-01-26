package com.example.ledger.domain.partner.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "partner")
public class Partner {
    // 거래처(공급처/고객)
    // 발주는 공급처, 매출은 고객으로 연결됨
    // 그래서 해당 레포는 거래처 등록/조회/비활성 처리되어야 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private PartnerType type;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PartnerStatus status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Partner() {
    }

    public Partner(
            Long id,
            PartnerType type,
            String name,
            PartnerStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PartnerStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public PartnerType getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
