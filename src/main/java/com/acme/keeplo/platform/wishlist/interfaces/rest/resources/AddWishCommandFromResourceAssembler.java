package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

import com.acme.keeplo.platform.wishlist.domain.model.commands.AddWishCommand;

public class AddWishCommandFromResourceAssembler {
    public static AddWishCommand toCommandFromResource(AddWishResource resource) {
        return new AddWishCommand(
                resource.title(),
                resource.description(),
                resource.url(),
                resource.collectionId()
        );
    }
}