package com.acme.keeplo.platform.wishlist.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class CollectionId {

    private String value;

    public CollectionId() {
        this.value = UUID.randomUUID().toString();
    }

    public CollectionId(String value) {
        this.value = value;
    }
}