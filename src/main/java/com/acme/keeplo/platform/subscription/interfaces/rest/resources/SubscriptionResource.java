package com.acme.keeplo.platform.subscription.interfaces.rest.resources;


import com.acme.keeplo.platform.users.domain.model.aggregates.Users;

public record SubscriptionResource(
        MembershipResource membership,
        PaymentCardResource paymentCard
) {}