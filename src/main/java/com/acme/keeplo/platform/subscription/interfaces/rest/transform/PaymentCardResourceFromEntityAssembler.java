package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.PaymentCardResource;

public class PaymentCardResourceFromEntityAssembler {
    public static PaymentCardResource toResourceFromEntity(PaymentCard card) {
        return new PaymentCardResource(
                card.getId(),
                card.getCardNumber().value(),
                card.getHolderName(),
                card.getExpirationDate(),
                card.getCvv()
        );
    }
}