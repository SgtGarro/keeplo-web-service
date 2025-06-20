package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record CreatePaymentCardResource(String cardNumber, String holderName, Date expirationDate, String cvv) {
    public CreatePaymentCardResource {
        if (cardNumber == null)
            throw new IllegalArgumentException("cardNumber must be provided.");
        if (holderName == null)
            throw new IllegalArgumentException("holderName must be provided.");
        if (expirationDate == null)
            throw new IllegalArgumentException("expirationDate must be provided");
        if (cvv == null)
            throw new IllegalArgumentException("cvv must be provided");
    }

}
