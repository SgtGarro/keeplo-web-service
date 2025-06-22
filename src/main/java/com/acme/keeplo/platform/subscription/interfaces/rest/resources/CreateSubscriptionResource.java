package com.acme.keeplo.platform.subscription.interfaces.rest.resources;


/**
 * Resource representing the data required to create a new subscription.
 * This resource is typically used in HTTP POST requests to create a subscription.
 * It includes identifiers for the user, the selected membership, and optionally a payment card.
 *
 * @param userId        the unique identifier of the user creating the subscription
 * @param membershipId  the unique identifier of the selected membership
 * @param paymentCardId the unique identifier of the associated payment card, required only for non-free memberships
 * @throws IllegalArgumentException if userId or membershipId are null or non-positive,
 *                                  or if paymentCardId is provided and is non-positive
 */
public record CreateSubscriptionResource(
        Long userId,
        Long membershipId,
        Long paymentCardId
) {
    /**
     * Constructs a CreateSubscriptionResource with input validation.
     *
     * @throws IllegalArgumentException if:
     *                                  - userId is null or not positive
     *                                  - membershipId is null or not positive
     *                                  - paymentCardId is not null and not positive
     */
    public CreateSubscriptionResource {
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("userId must be positive.");
        if (membershipId == null || membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}