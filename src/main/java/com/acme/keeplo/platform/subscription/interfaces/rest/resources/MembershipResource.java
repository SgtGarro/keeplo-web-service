package com.acme.keeplo.platform.subscription.interfaces.rest.resources;
/**
 * Resource representing a membership in the subscription system.
 * This resource is typically used in API responses to provide membership details
 * to clients, including name, price, and description.
 *
 * @param id          the unique identifier of the membership
 * @param name        the name of the membership plan
 * @param price       the cost associated with the membership (0.0 means it's free)
 * @param description a textual description of the membership and its benefits
 */
public record MembershipResource(
        Long id,
        String name,
        float price,
        String description
) {}