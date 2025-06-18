package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;

import java.util.Optional;

public interface CollectionCommandService {
    Optional<Collection> handle(CreateCollectionCommand command);
}
