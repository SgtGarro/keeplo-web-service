package com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions;


public record DeleteSubscriptionCommand(Long subscriptionId) {
    public DeleteSubscriptionCommand {
        if (subscriptionId <= 0)
            throw new IllegalArgumentException("subscriptionId must be positive.");
    }
}