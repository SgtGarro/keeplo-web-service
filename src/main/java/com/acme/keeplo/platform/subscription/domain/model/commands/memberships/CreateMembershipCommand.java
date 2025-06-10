package com.acme.keeplo.platform.subscription.domain.model.commands.memberships;


public record CreateMembershipCommand(String name, float price, String description) {

    public CreateMembershipCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name is required.");
        if (price < 0)
            throw new IllegalArgumentException("price cannot be negative.");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description is required.");
    }
}