package com.acme.keeplo.platform.iam.application.internal.commandservices;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.domain.model.commands.SignInCommand;
import com.acme.keeplo.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.keeplo.platform.iam.domain.model.entities.Role;
import com.acme.keeplo.platform.iam.domain.model.valueobjects.Roles;
import com.acme.keeplo.platform.iam.domain.services.UserCommandService;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.acme.keeplo.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptHashingService hashingService;
    private final BearerTokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptHashingService hashingService, BearerTokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.username());
        if (user.isEmpty()) {
            return Optional.empty();
        }

        if (!hashingService.matches(command.password(), user.get().getPassword())) {
            return Optional.empty();
        }


        var token = tokenService.generateToken(user.get().getEmail(), user.get().getRoles());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException(String.format("User with email %s already exists", command.email()));
        }

        var hashedPassword = hashingService.encode(command.password());

        var defaultRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Default role ROLE_USER not found. Please seed roles."));

        var user = new User(command.email(), hashedPassword, command.name(), command.profilePicture());
        user.addRole(defaultRole);

        var createdUser = userRepository.save(user);

        return Optional.of(createdUser);
    }
}