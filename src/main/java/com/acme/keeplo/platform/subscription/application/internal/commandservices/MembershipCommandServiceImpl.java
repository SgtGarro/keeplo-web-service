package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.services.MembershipCommandService;

import java.util.Optional;

public class MembershipCommandServiceImpl implements MembershipCommandService {

    @Override
    public Optional<Subscription> handle(CreateMembershipCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<Subscription> handle(UpdateMembershipCommand command) {
        return Optional.empty();
    }
}
