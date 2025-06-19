package com.acme.keeplo.platform.wishlist.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
public class Tag {

    private String name;
    private String color; // ejemplo: "#ffcc00"
    private String id;    // UUID como valor único

    protected Tag() {
        this.id = UUID.randomUUID().toString();
    }

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
