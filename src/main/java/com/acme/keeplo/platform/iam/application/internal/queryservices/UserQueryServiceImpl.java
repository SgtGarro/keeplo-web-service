package com.acme.keeplo.platform.iam.application.internal.queryservices;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.domain.model.queries.GetAllUsersByIdQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByIdAndEmailQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.keeplo.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.acme.keeplo.platform.iam.domain.services.UserQueryService;
import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByEmail(query.username());
    }

    @Override
    public Optional<User> handle(GetAllUsersByIdQuery query) {
        return userRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }

    @Override
    public Optional<User> handle(GetUserByIdAndEmailQuery query) {

        return userRepository.findById(query.id())
                .filter(user -> user.getEmail().equals(query.email()));
    }
}