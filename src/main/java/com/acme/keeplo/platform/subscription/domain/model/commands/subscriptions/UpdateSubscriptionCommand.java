package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;

/**
 * Command to update an existing subscription.
 *
 * This command allows changing the membership and optionally the payment card
 * associated with a subscription. Validation ensures that provided IDs are positive.
 * @param subscriptionId the ID of the subscription to update.
 * @param userId The ID of the user.
 * @param membershipId The ID of the new membership to assign.
 * @param paymentCardId (Optional) The ID of the new payment card to associate.
 */
public record UpdateSubscriptionCommand(
        Long subscriptionId,
        Long userId,
        Long membershipId,
        Long paymentCardId
) {
    public UpdateSubscriptionCommand {
        if (subscriptionId == null || subscriptionId <= 0)
            throw new IllegalArgumentException("subscriptionId must be positive.");
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("subscriptionId must be positive.");
        if (membershipId == null || membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (paymentCardId != null && paymentCardId <= 0)
            throw new IllegalArgumentException("paymentCardId must be positive if provided.");
    }
}