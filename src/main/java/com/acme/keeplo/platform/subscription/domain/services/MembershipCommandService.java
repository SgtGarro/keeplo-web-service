package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;

import java.util.Optional;

public interface MembershipCommandService {

    Optional<Subscription> handle(CreateMembershipCommand command);

    Optional<Subscription> handle(UpdateMembershipCommand command);

}
