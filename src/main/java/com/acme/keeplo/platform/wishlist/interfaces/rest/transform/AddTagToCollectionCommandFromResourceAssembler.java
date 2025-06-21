package com.acme.keeplo.platform.wishlist.interfaces.rest.transform;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddTagToCollectionCommand;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.AddTagToCollectionResource;

public class AddTagToCollectionCommandFromResourceAssembler {
    public static AddTagToCollectionCommand toCommand(Long collectionId, AddTagToCollectionResource resource) {
        return new AddTagToCollectionCommand(collectionId, resource.name(), resource.color());
    }
}
