package com.acme.keeplo.platform.subscription.domain.model.commands.memberships;

/**
 * Command to update an existing membership.
 *
 * Ensures that the membership ID is positive, the name and description are not blank,
 * and the price is not negative.
 *
 * @param membershipId The ID of the membership to be updated.
 * @param name The updated name of the membership.
 * @param price The updated price of the membership.
 * @param description The updated description of the membership.
 */
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