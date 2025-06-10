package com.acme.keeplo.platform.subscription.interfaces.rest.resources;

public record MembershipResource(
        Long id,
        String name,
        float price,
        String description
) {}