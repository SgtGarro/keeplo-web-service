package com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findByCollection_Id(Long collectionId);
}
