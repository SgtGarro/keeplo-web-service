package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

/**
 * Resource representing a user's subscription details.
 * This resource is used to expose subscription information through the API.
 * It includes the subscription ID, the associated user ID, the membership plan,
 * and the linked payment card if applicable.
 *
 * @param id          the unique identifier of the subscription
 * @param userId      the identifier of the user who owns the subscription
 * @param membership  the membership plan associated with the subscription
 * @param paymentCard the payment card used for the subscription
 */

public record SubscriptionResource(
        Long id,
        Long userId,
        MembershipResource membership,
        PaymentCardResource paymentCard
) {}