package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddWishCommand;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectionCommandServiceImpl implements CollectionCommandService {

    private final CollectionRepository collectionRepository;

    public CollectionCommandServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Optional<Collection> handle(CreateCollectionCommand command) {
        Collection collection = new Collection(command.name(), command.description(), command.isPublic());
        collectionRepository.save(collection);
        return Optional.of(collection);
    }

    @Override
    public Optional<Collection> handle(AddWishCommand command) {
        var collection = collectionRepository.findById(command.collectionId());
        if (collection.isEmpty()) return Optional.empty();

        var wish = new Wish(command.title(), command.description(), command.url(), collection.get());
        collection.get().addWish(wish);

        collectionRepository.save(collection.get());
        return collection;
    }
}
