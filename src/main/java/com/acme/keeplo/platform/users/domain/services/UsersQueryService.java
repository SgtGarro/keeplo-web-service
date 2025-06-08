package com.acme.keeplo.platform.users.domain.services;

import com.acme.keeplo.platform.users.domain.model.aggregates.Users;

import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersByIdQuery;

import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetUserByIdAndEmailQuery;

import java.util.List;

import java.util.Optional;


/**
 * @name UsersQueryService
 *
 * @summary
 * This interface represents the service to handle users queries.
 * @since 1.0.0
 */
public interface UsersQueryService {
    /**
     * Handles the get all users query.
     * @param query The "get all users" query.
     * @return The list of users if exists.
     * @throws IllegalArgumentException If empty
     * @see GetAllUsersQuery
     */
    List<Users> handle(GetAllUsersQuery query);
    /**
     * Handles the get user by id query.
     * @param query The get user by id query.
     * @return The user if exists.
     * @throws IllegalArgumentException If id is null or empty
     * @see GetAllUsersByIdQuery
     */
    Optional<Users> handle(GetAllUsersByIdQuery query);

    Optional<Users> handle(GetUserByEmailQuery query);

    Optional<Users> handle(GetUserByIdAndEmailQuery query);
  }

