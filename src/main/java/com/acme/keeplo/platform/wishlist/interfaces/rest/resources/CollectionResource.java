package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record CollectionResource(
        String id,
        String name,
        String description,
        boolean isPublic
) {}
