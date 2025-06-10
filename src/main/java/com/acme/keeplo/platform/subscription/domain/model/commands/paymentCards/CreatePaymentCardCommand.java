package com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards;

import java.util.Date;

public record CreatePaymentCardCommand(
        String cardNumber,
        String holderName,
        Date expirationDate,
        String cvv
) {
    public CreatePaymentCardCommand {
        if (cardNumber == null || cardNumber.isBlank())
            throw new IllegalArgumentException("cardNumber is required.");
        if (holderName == null || holderName.isBlank())
            throw new IllegalArgumentException("holderName is required.");
        if (expirationDate == null)
            throw new IllegalArgumentException("expirationDate is required.");
        if (cvv == null || cvv.isBlank())
            throw new IllegalArgumentException("cvv is required.");
    }
}