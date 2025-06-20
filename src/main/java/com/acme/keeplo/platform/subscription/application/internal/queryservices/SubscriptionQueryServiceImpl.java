package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Service implementation for handling queries related to subscriptions.
 *
 * Provides a method to retrieve a subscription by user ID.
 */
@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Constructs the query service with the required subscription repository.
     *
     * @param subscriptionRepository subscription data access layer
     */
    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Handles the retrieval of a subscription by the user ID.
     *
     * @param query the query containing the user ID
     * @return an Optional containing the subscription if found, or empty otherwise
     */
    @Override
    public Optional<Subscription> handle(GetSubscriptionByUserIdQuery query) {
        return subscriptionRepository.findByUserId(query.userId());
    }
}