package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardByIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardQueryService;

import java.util.List;

public class PaymentCardQueryServiceImpl implements PaymentCardQueryService {
    @Override
    public List<Subscription> handle(GetPaymentCardByIdQuery query) {
        return List.of();
    }
}
