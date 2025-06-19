package com.acme.keeplo.platform.wishlist.application.internal.queryservices;

import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import com.acme.keeplo.platform.wishlist.infrastructure.persistence.jpa.repositories.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishQueryService {

    private final WishRepository wishRepository;

    public WishQueryService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Optional<Wish> getById(Long id) {
        return wishRepository.findById(id);
    }

    public List<Wish> getByCollectionId(Long collectionId) {
        return wishRepository.findByCollection_Id(collectionId);
    }
    public Wish save(Wish wish) {
        return wishRepository.save(wish);
    }
    public void deleteById(Long id) {
        wishRepository.deleteById(id);
    }
}
