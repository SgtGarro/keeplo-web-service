package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.SubscriptionResource;

/**
 * Assembler class to convert Subscription entities into SubscriptionResource DTOs.
 */
public class SubscriptionResourceFromEntityAssembler {

    /**
     * Converts a Subscription entity into a SubscriptionResource.
     *
     * @param subscription the Subscription entity to convert
     * @return a SubscriptionResource representing the subscription, or null if the input is null
     */
    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        if (subscription == null) return null;

        var membershipResource = MembershipResourceFromEntityAssembler
                .toResourceFromEntity(subscription.getMembership());

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