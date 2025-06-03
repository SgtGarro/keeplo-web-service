package com.acme.keeplo.platform.userManagement.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.userManagement.domain.model.aggregates.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>{
    /**
     * Find all users.
     * @return List of Users
     */
    List<Users> findAllUsers();

    /**
     * Find a User by ID.
     * @param Id User ID
     * @return User
     */
    Optional<Users> findUsersById(String Id);

}
