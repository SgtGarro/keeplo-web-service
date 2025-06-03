package com.acme.keeplo.platform.userManagement.application.internal.queryservices;

import com.acme.keeplo.platform.userManagement.domain.model.aggregates.Users;

import com.acme.keeplo.platform.userManagement.domain.model.queries.GetAllUsersByIdQuery;

import com.acme.keeplo.platform.userManagement.domain.model.queries.GetAllUsersQuery;

import com.acme.keeplo.platform.userManagement.domain.services.UsersQueryService;

import com.acme.keeplo.platform.userManagement.infrastructure.persistence.jpa.UsersRepository;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

public class UsersQueryServiceImpl implements UsersQueryService{

    private final UsersRepository usersRepository;

    public UsersQueryServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Users> handle(GetAllUsersQuery query) {
        return usersRepository.findAllUsers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Users> handle(GetAllUsersByIdQuery query) {
        return usersRepository.findById(query.id());
    }

}
