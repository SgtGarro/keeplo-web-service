package com.acme.keeplo.platform.subscription.domain.model.commands.memberships;

/**
 * Command to create a new membership.
 *
 * Validates that name and description are not blank,
 * and that the price is not negative.
 *
 * @param name The name of the membership.
 * @param price The price of the membership.
 * @param description A brief description of the membership.
 */
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