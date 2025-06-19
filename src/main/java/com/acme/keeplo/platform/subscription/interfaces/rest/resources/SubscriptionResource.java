package com.acme.keeplo.platform.subscription.interfaces.rest.resources;



public record SubscriptionResource(
        Long id,
        Long userId,
        MembershipResource membership,
        PaymentCardResource paymentCard
) {}