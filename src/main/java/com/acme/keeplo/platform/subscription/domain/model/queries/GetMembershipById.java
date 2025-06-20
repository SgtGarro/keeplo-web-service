package com.acme.keeplo.platform.subscription.domain.model.queries;
/**
 * Query to retrieve a membership by its ID.
 *
 * This query object is used to request a specific membership from the system
 * based on a unique identifier.
 *
 * @param Id the ID of the membership to retrieve. Must be a positive value.
 * @throws IllegalArgumentException if the provided ID is not positive.
 */
public record GetMembershipById(Long Id) {
    public GetMembershipById{
        if (Id <= 0)
            throw new IllegalArgumentException("Id must be positive.");
    }

}
