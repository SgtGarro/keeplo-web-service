package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.PaymentCardResource;


/**
 * Assembler class to transform a PaymentCard entity into a PaymentCardResource DTO.
 */
public class PaymentCardResourceFromEntityAssembler {

    /**
     * Converts a PaymentCard entity into a PaymentCardResource.
     *
     * @param card the PaymentCard entity to convert
     * @return a PaymentCardResource representing the entity
     */
    public static PaymentCardResource toResourceFromEntity(PaymentCard card) {
        return new PaymentCardResource(
                card.getId(),
                card.getCardNumber(),
                card.getHolderName(),
                card.getExpirationDate(),
                card.getCvv()
        );
    }
}
