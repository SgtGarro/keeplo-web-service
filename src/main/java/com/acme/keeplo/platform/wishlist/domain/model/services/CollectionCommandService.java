package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddTagToCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddWishCommand;
import java.util.Optional;

public interface CollectionCommandService {
    Optional<Collection> handle(CreateCollectionCommand command);
    boolean deleteById(Long id);
    boolean addTagToCollection(AddTagToCollectionCommand command);
}
