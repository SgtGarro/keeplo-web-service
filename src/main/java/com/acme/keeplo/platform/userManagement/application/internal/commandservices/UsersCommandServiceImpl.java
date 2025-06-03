package com.acme.keeplo.platform.userManagement.application.internal.commandservices;

import com.acme.keeplo.platform.userManagement.domain.model.aggregates.Users;

import com.acme.keeplo.platform.userManagement.domain.model.commands.CreateUsersCommand;

import com.acme.keeplo.platform.userManagement.domain.services.UsersCommandService;

import com.acme.keeplo.platform.userManagement.infrastructure.persistence.jpa.UsersRepository;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * UsersCommandService Implementation
 *
 * @summary
 * Implementation of the UsersCommandService interface.
 * It is responsible for handling Users commands.
 *
 * @since 1.0
 */

@Service
public class UsersCommandServiceImpl implements UsersCommandService {

    private final UsersRepository usersRepository;

    public UsersCommandServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Users> handle(CreateUsersCommand command) {
        //validate if exists
        if (usersRepository.existsByEmail(command.email()))
            throw new IllegalArgumentException("User with same email already exists for this API key");

        //create new user
        var user = new Users(command);

        //save in database
        var createdUser = usersRepository.save(user);

        //return in optional
        return Optional.of(createdUser);
    }
}
