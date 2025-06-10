package com.acme.keeplo.platform.subscription.interfaces.rest.resources;


public record SubscriptionResource(
        Long userId,
        MembershipResource membership,
        PaymentCardSummaryResource paymentCard // puede ser null si es free
) {}