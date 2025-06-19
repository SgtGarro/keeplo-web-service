package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.CreateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.memberships.UpdateMembershipCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.services.MembershipCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<Memberships> handle(CreateMembershipCommand command) {
        var newMembership = new Memberships(command.name(), command.price(), command.description());

        membershipRepository.save(newMembership);
        return Optional.of(newMembership);
    }

    @Override
    public Optional<Memberships> handle(UpdateMembershipCommand command) {
        var existingMembership = membershipRepository.findById(command.membershipId());
        if (existingMembership.isEmpty()) {
            return Optional.empty(); // Return empty if membership not found
        }
        var membershipToUpdate = existingMembership.get();

        membershipToUpdate.update(command.name(), command.price(), command.description());

        membershipRepository.save(membershipToUpdate);
        return Optional.of(membershipToUpdate);
    }
}