package com.acme.keeplo.platform.wishlist.domain.model.services;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetAllWishesByCollectionId;
import com.acme.keeplo.platform.wishlist.domain.model.queries.GetWishById;

import java.util.List;
import java.util.Optional;

public interface WishQueryService {

    Optional<Wish> handle(GetWishById query);
    List<Wish> handle(GetAllWishesByCollectionId query);

}
