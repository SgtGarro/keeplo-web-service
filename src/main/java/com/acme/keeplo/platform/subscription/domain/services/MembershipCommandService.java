package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;

import java.util.Optional;

public interface MembershipCommandService {
    Optional<Memberships> handle(CreateMembershipCommand command); // Cambiado a Memberships
    Optional<Memberships> handle(UpdateMembershipCommand command); // Cambiado a Memberships
}