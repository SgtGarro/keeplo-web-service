package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter; // Añadir Setter para permitir actualización

import java.util.Date;

@Getter
@Setter
@Entity
public class PaymentCard extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String cvv;

    protected PaymentCard() {

        this.cardNumber = null;
        this.holderName = "";
        this.expirationDate = null;
        this.cvv = "";
    }


    public PaymentCard(String cardNumber, String holderName, Date expirationDate, String cvv) {

        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty.");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null.");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be null or empty.");
        }
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public void update(String cardNumber, String holderName, Date expirationDate, String cvv) {
        if (cardNumber == null) {
            throw new IllegalArgumentException("Card number cannot be null.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty.");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null.");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("CVV cannot be null or empty.");
        }
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}