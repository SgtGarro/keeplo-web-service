package com.acme.keeplo.platform.wishlist.application.internal.commandservices;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.commands.AddWishCommand;
import com.acme.keeplo.platform.wishlist.domain.model.commands.CreateCollectionCommand;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishCommandService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WishCommandServiceImpl implements WishCommandService {
    private final WishRepository wishRepository;
    private final CollectionRepository collectionRepository;

    public WishCommandServiceImpl(WishRepository wishRepository, CollectionRepository collectionRepository) {
        this.wishRepository = wishRepository;
        this.collectionRepository = collectionRepository;
    }


    @Override
    public Optional<Wish> handle(AddWishCommand command) {
        Optional<Collection> optionalCollection = collectionRepository.findById(command.collectionId());

        if (optionalCollection.isEmpty()) {
            return Optional.empty();
        }

        Wish wish = new Wish(
                command.title(),
                command.description(),
                command.url(),
                optionalCollection.get()
        );

        wishRepository.save(wish);
        return Optional.of(wish);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!wishRepository.existsById(id)) return false;
        wishRepository.deleteById(id);
        return true;
    }



}
