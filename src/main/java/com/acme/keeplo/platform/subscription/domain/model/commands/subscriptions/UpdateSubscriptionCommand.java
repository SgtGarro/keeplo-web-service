package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;

import java.util.Date;

public record UpdateSubscriptionCommand(
        Long subscriptionId,
        Long userId,
        Long membershipId,
        Long paymentCardId
) {
    public UpdateSubscriptionCommand {
        if (userId <= 0)
            throw new IllegalArgumentException("userId must be positive.");
        if (subscriptionId <= 0)
            throw new IllegalArgumentException("subscriptionId must be positive.");
        if (membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}


