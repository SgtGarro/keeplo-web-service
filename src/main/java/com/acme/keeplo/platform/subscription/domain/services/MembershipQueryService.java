package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetMembershipById;

import java.util.List;
import java.util.Optional;

public interface MembershipQueryService {
    List<Subscription> handle(GetAllMemberships query);

    Optional<Subscription> handle(GetMembershipById query);
}
