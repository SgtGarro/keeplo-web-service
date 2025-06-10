package com.acme.keeplo.platform.subscription.interfaces.rest.resources;



public record CreateSubscriptionResource(
        Long userId,
        Long membershipId,
        Long paymentCardId
) {
    public CreateSubscriptionResource {
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("userId must be positive.");
        if (membershipId == null || membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}