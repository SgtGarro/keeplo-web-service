package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        if (subscription == null) return null;

        var membershipResource =
                MembershipResourceFromEntityAssembler.toResourceFromEntity(subscription.getMembership());

        var paymentCardResource = subscription.getPaymentCard() != null
                ? PaymentCardResourceFromEntityAssembler.toResourceFromEntity(subscription.getPaymentCard())
                : null;

        return new SubscriptionResource(
                subscription.getId(),
                subscription.getUser().getId(),
                membershipResource,
                paymentCardResource
        );
    }
}