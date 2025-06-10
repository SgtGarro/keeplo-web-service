package com.acme.keeplo.platform.subscription.domain.model.commands.memberships;

public record UpdateMembershipCommand(
        Long membershipId,
        String name,
        float price,
        String description
) {
    public UpdateMembershipCommand {
        if (membershipId <= 0)
            throw new IllegalArgumentException("membershipId must be positive.");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name is required.");
        if (price < 0)
            throw new IllegalArgumentException("price cannot be negative.");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description is required.");
    }
}