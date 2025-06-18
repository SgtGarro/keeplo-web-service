package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.CollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, CollectionId> {
    Optional<Collection> findById(CollectionId id);
}
