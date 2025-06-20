package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentCardQueryService {
    Optional<PaymentCard> handle(GetPaymentCardByIdQuery query);
  }
