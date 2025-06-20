package com.acme.keeplo.platform.subscription.domain.services;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;

import java.util.Optional;

/**
 * Service interface for handling membership-related commands.
 *
 * Provides methods to create and update membership entities.
 */
public interface MembershipCommandService {

    /**
     * Handles the creation of a new membership.
     *
     * @param command the command containing the data needed to create the membership
     * @return an Optional containing the created Memberships entity, or empty if creation fails
     */
    Optional<Memberships> handle(CreateMembershipCommand command);

    /**
     * Handles the update of an existing membership.
     *
     * @param command the command containing the data needed to update the membership
     * @return an Optional containing the updated Memberships entity, or empty if the membership is not found
     */
    Optional<Memberships> handle(UpdateMembershipCommand command);
}