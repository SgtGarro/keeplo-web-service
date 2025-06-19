package com.acme.keeplo.platform.iam.domain.model.queries;

/**
 * Query to retrieve a user by their username.
 * In this system, the 'username' maps to the user's email address.
 * It is crucial for authentication processes where the user is looked up by their unique login identifier.
 *
 * @param username The username (email) to search for.
 */
public record GetUserByUsernameQuery(String username) {
    public GetUserByUsernameQuery {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
    }
}