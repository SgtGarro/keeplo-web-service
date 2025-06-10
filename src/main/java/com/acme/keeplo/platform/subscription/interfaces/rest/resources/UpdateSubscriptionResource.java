package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

public record UpdateSubscriptionResource(
        Long userId,
        Long membershipId,
        Long paymentCardId
) {
    public UpdateSubscriptionResource {
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (membershipId == null || membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}