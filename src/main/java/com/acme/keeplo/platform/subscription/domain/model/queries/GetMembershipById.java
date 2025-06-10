package com.acme.keeplo.platform.subscription.domain.model.queries;

public record GetMembershipById(Long Id) {
    public GetMembershipById{
        if (Id <= 0)
            throw new IllegalArgumentException("Id must be positive.");
    }

}
