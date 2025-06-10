package com.acme.keeplo.platform.subscription.domain.model.queries;

/**
 * @summary
 * This class represents the query to get a subscription by the user ID.
 * @param userId - the ID of the user
 */
public record GetSubscriptionByUserIdQuery(Long userId) {
    public GetSubscriptionByUserIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId must be a positive number");
        }
    }
}