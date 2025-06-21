package com.acme.keeplo.platform.wishlist.application.internal.queryservices;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllWishesByCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetCollectionByIdQuery;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetWishById;
import com.acme.keeplo.platform.wishlist.domain.model.services.WishQueryService;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.CollectionRepository;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishQueryServiceImpl implements WishQueryService {

    private final WishRepository wishRepository;

    public WishQueryServiceImpl(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Override
    public Optional<Wish> handle(GetWishById query) {
        return wishRepository.findById(query.id());
    }

    @Override
    public List<Wish> handle(GetAllWishesByCollectionId query) {
        return wishRepository.findByCollectionId(query.collectionId());
    }
}
