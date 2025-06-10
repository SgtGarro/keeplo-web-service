package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.PaymentCardSummaryResource;

public class PaymentCardResourceFromEntityAssembler {
    public static PaymentCardSummaryResource toResourceFromEntity(PaymentCard card) {
        return new PaymentCardSummaryResource(
                card.getId(),
                card.getCardNumber().value(),
                card.getHolderName(),
                card.getExpirationDate(),
                card.getCvv()
        );
    }
}