package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.subscription.domain.model.valueObjects.CardNumber;
import jakarta.persistence.Column; // Importar Column
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter; // Añadir Setter para permitir actualización

import java.util.Date;

@Getter // Genera getters para todos los campos
@Setter // Añadir @Setter si quieres usar setters para la actualización
@Entity
public class PaymentCard extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false) // Asegurarse de que el número de tarjeta no sea nulo
    private CardNumber cardNumber;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String cvv;

    // Constructor vacío para JPA
    protected PaymentCard() {
        // Inicializar con valores por defecto si es necesario
        this.cardNumber = null; // o new CardNumber("0000000000000000") si es válido
        this.holderName = "";
        this.expirationDate = null;
        this.cvv = "";
    }

    // Constructor para crear nuevas instancias de PaymentCard desde el servicio de comandos
    public PaymentCard(CardNumber cardNumber, String holderName, Date expirationDate, String cvv) {
        // Validación básica, las validaciones completas están en CardNumber y/o el comando
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

    // Método para actualizar las propiedades de una tarjeta de pago existente
    public void update(CardNumber cardNumber, String holderName, Date expirationDate, String cvv) {
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