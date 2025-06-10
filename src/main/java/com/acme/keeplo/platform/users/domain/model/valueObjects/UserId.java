package com.acme.keeplo.platform.users.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long value) {
    public UserId {
        if (value == null) throw new IllegalArgumentException("UserId cannot be null");
    }
}