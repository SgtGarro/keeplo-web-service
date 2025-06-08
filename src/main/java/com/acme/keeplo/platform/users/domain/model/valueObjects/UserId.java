package com.acme.keeplo.platform.users.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the user id.
 * @summary
 * This value object is used to represent the user id. It is an embeddable object that is used to represent the user id in the user entity.
 * It throws an IllegalArgumentException if the profile id is null or less than 1.
 * @param userId The profile id. It cannot be null or less than 1.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record UserId(Long userId) {
    public UserId{
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        }
    }
}
