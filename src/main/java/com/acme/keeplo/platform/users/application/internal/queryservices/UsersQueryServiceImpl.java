package com.acme.keeplo.platform.users.application.internal.queryservices;

import com.acme.keeplo.platform.users.domain.model.aggregates.Users;

import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersByIdQuery;

import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersQuery;

import com.acme.keeplo.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetUserByIdAndEmailQuery;
import com.acme.keeplo.platform.users.domain.services.UsersQueryService;

import com.acme.keeplo.platform.users.infrastructure.persistence.jpa.UsersRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.lang.NonNull;

@Service
public class UsersQueryServiceImpl implements UsersQueryService{

    private final UsersRepository usersRepository;

    public UsersQueryServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public List<Users> handle(GetAllUsersQuery query) {
        return usersRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Users> handle(GetAllUsersByIdQuery query) {
        return usersRepository.findById(query.id());
    }

    @Override
    public Optional<Users> handle(GetUserByEmailQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Users> handle(GetUserByIdAndEmailQuery query) {
        return Optional.empty();
    }

}
