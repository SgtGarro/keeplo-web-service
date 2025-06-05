package com.acme.keeplo.platform.userManagement.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.userManagement.domain.model.aggregates.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>{
    /**
     * Find all users.
     * @return List of Users
     */
    List<Users> findAll();

    /**
     * Find a User by email.
     * @param email User email
     * @return User
     */
    Optional<Users> findByEmail(String email);
    /**
     * Check if a user exists by email.
     * @param email user email
     * @return True if exists, false otherwise
     */
    boolean existsByEmail(String email);
}
