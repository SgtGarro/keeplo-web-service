package com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Memberships, Long> {
    List<Memberships> findAll();

    Optional<Memberships> findById(Long id);
}
