package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record WishResource(Long id,
                           String title,
                           String description,
                           String url,
                           Long collectionId) {
}
