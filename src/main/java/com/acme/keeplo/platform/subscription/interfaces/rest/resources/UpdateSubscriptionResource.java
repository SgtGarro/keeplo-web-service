package com.acme.keeplo.platform.subscription.interfaces.rest.resources;
/**
 * Resource used to update a subscription with new membership or payment card information.
 * This resource is used as part of the request body when updating an existing subscription.
 *
 * @param userId         the ID of the user who owns the subscription
 * @param membershipId   the new membership ID to be applied
 * @param paymentCardId  the ID of the payment card to be associated, if required
 */
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