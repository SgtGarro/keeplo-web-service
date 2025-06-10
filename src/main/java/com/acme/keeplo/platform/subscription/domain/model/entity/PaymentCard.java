package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.subscription.domain.model.valueObjects.CardNumber;
import jakarta.persistence.Entity;
import lombok.Getter;
import jakarta.persistence.*;

import java.util.Date;

@Getter
@Entity
public class PaymentCard extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CardNumber cardNumber;
    private String holderName;
    private Date expirationDate;
    private String cvv;
}
