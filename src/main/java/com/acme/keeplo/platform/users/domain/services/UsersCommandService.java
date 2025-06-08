package com.acme.keeplo.platform.users.domain.services;

import com.acme.keeplo.platform.users.domain.model.aggregates.Users;

import com.acme.keeplo.platform.users.domain.model.commands.CreateUsersCommand;

import java.util.Optional;

/**
 * @name UsersCommandService
 * @summary
 * This interface represents the service to handle Users commands.
 */

public interface UsersCommandService {
    /**
     * Handles the create User command.
     * @param command The create user command.
     * @return The created user.
     *
     * @throws IllegalArgumentException If ID is null or empty
     * @see CreateUsersCommand
     */
    Optional<Users> handle(CreateUsersCommand command);

}
