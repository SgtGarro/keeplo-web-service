package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record CreateCollectionResource(
        String name,
        String description,
        boolean isPublic
) {
    public CreateCollectionResource {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description is required");
    }
}