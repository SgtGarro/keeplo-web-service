package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetMembershipById;
import com.acme.keeplo.platform.subscription.domain.services.MembershipQueryService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Ensure this annotation is present for Spring to detect it
public class MembershipQueryServiceImpl implements MembershipQueryService {

    private final MembershipRepository membershipRepository;

    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Memberships> handle(GetAllMemberships query) {
        // The repository naturally returns a List<Memberships>
        return membershipRepository.findAll();
    }

    @Override
    public Optional<Memberships> handle(GetMembershipById query) {
        // The repository's findById naturally returns Optional<Memberships>
        return membershipRepository.findById(query.Id());
    }
}