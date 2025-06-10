package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetMembershipById;
import com.acme.keeplo.platform.subscription.domain.services.MembershipQueryService;

import java.util.List;
import java.util.Optional;

public class MembershipQueryServiceImpl implements MembershipQueryService {
    @Override
    public List<Subscription> handle(GetAllMemberships query) {
        return List.of();
    }

    @Override
    public Optional<Subscription> handle(GetMembershipById query) {
        return Optional.empty();
    }
}
