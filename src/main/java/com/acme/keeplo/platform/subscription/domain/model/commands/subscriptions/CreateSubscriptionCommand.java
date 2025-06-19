package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;


public record CreateSubscriptionCommand(Long userId, Long membershipId, Long paymentCardId) {

    /**
     * Validates the command inputs.
     *
     * @throws IllegalArgumentException if any required field is invalid
     */
    public CreateSubscriptionCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId must be a positive number.");
        }
        if (membershipId == null || membershipId <= 0) {
            throw new IllegalArgumentException("MembershipId must be a positive number.");
        }

        if (paymentCardId != null && paymentCardId <= 0) {
            throw new IllegalArgumentException("PaymentCardId must be positive if provided.");
        }
    }
}