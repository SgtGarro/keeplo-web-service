package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;


public record CreateSubscriptionCommand( Long membershipId,
                                         Long paymentCardId,
                                          Long userId) {

    /**
     * Validates the command inputs.
     *
     * @throws IllegalArgumentException if any required field is invalid
     */
    public CreateSubscriptionCommand {

        if (membershipId <= 0) {
            throw new IllegalArgumentException("MembershipId must be a positive number.");
        }
        if (paymentCardId != null && paymentCardId <= 0) {
            throw new IllegalArgumentException("PaymentCardId must be positive if provided.");
        }
        if (userId != null && userId <= 0) {
            throw new IllegalArgumentException("userId must be positive if provided.");
        }
}}