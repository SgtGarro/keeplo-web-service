package com.acme.keeplo.platform.wishlist.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.util.Objects;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionId that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}