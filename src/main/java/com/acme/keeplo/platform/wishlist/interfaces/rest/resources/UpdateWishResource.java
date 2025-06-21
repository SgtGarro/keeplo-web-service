package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record UpdateWishResource(
        String title,
        String description,
        String url
) {}
