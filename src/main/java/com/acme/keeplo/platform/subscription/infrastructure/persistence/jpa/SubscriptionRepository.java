package com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserId(Long userId);

}