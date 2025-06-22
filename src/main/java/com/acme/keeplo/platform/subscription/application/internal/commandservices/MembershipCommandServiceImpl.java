package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.services.MembershipCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Service that handles membership-related command operations.
 *
 * Provides the logic for creating and updating memberships in the system.
 */
@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    /**
     * Constructs the service with the required repository dependency.
     *
     * @param membershipRepository the repository used for persistence operations
     */
    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * Handles the creation of a new membership.
     *
     * @param command the command containing membership creation data
     * @return an optional containing the created membership
     */
    @Override
    public Optional<Memberships> handle(CreateMembershipCommand command) {
        var newMembership = new Memberships(command.name(), command.price(), command.description());

        membershipRepository.save(newMembership);
        return Optional.of(newMembership);
    }

    /**
     * Handles the update of an existing membership.
     *
     * @param command the command containing updated membership data
     * @return an optional containing the updated membership, or empty if not found
     */
    @Override
    public Optional<Memberships> handle(UpdateMembershipCommand command) {
        var existingMembership = membershipRepository.findById(command.membershipId());
        if (existingMembership.isEmpty()) {
            return Optional.empty();
        }

        var membershipToUpdate = existingMembership.get();
        membershipToUpdate.update(command.name(), command.price(), command.description());

        membershipRepository.save(membershipToUpdate);
        return Optional.of(membershipToUpdate);
    }
}