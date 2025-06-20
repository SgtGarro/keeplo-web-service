package com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * Repository interface for accessing membership data.
 *
 * Extends JpaRepository to provide basic CRUD operations for Memberships entities.
 */
public interface MembershipRepository extends JpaRepository<Memberships, Long> {

    /**
     * Retrieves all memberships from the database.
     *
     * @return a list of all Memberships entities
     */
    List<Memberships> findAll();

    /**
     * Retrieves a membership by its ID.
     *
     * @param id the ID of the membership
     * @return an Optional containing the Memberships entity if found, or empty if not
     */
    Optional<Memberships> findById(Long id);
}