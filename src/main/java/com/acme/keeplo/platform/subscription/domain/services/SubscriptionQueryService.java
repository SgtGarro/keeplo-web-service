package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;

import java.util.List;
import java.util.Optional;


/**
 * @name SubscriptionQueryService
 *
 * @summary
 * This interface represents the service to handle v queries.
 * @since 1.0.0
 */
public interface SubscriptionQueryService {

    Optional<Subscription> handle(GetSubscriptionByUserIdQuery query);}


