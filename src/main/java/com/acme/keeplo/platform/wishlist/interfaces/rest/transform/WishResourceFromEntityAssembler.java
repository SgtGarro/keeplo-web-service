package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.WishResource;

public class WishResourceFromEntityAssembler {

    public static WishResource toResourceFromEntity(Wish wish) {
        return new WishResource(
                wish.getId(),
                wish.getTitle(),
                wish.getDescription(),
                wish.getUrl(),
                wish.getCollection().getId()
        );
    }
}
