package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;

import java.util.Date;

public record UpdateSubscriptionCommand(
        Long subscriptionId,
        Long membershipId, // Solo se actualiza la membresía y/o la tarjeta
        Long paymentCardId
) {
    public UpdateSubscriptionCommand {
        if (subscriptionId == null || subscriptionId <= 0)
            throw new IllegalArgumentException("subscriptionId must be positive.");
        if (membershipId == null || membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        // paymentCardId puede ser nulo
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}