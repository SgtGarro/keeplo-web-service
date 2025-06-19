package com.acme.keeplo.platform.iam.domain.services;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.domain.model.queries.GetAllUsersByIdQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByIdAndEmailQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Query service interface for user management within the IAM module.
 * Defines operations related to retrieving user information.
 */
public interface UserQueryService {
    /**
     * Handles the query to retrieve all users.
     * @param query The {@link GetAllUsersQuery}.
     * @return A list of all {@link User} entities.
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handles the query to retrieve a user by their ID.
     * @param query The {@link GetUserByIdQuery} containing the user's ID.
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handles the query to retrieve a user by their unique identifier (email).
     * @param query The {@link GetUserByUsernameQuery} containing the user's email (username).
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetUserByUsernameQuery query);

    /**
     * Handles the query to retrieve a user by their ID.
     * @param query The {@link GetAllUsersByIdQuery} containing the user's ID.
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetAllUsersByIdQuery query);

    /**
     * Handles the query to retrieve a user by their email address.
     * @param query The {@link GetUserByEmailQuery} containing the user's email address.
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetUserByEmailQuery query);

    /**
     * Handles the query to retrieve a user by their ID and email address.
     * @param query The {@link GetUserByIdAndEmailQuery} containing the user's ID and email address.
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetUserByIdAndEmailQuery query);
}
