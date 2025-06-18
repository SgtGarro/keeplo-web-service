package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record PaymentCardResource(
        Long id,
        String cardNumber,
        String holderName,
        Date expirationDate,
        String Cvv
) {}
