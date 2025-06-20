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

/**
 * Service implementation for handling queries related to memberships.
 *
 * Provides access to all memberships and membership details by ID.
 */
@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {

    private final MembershipRepository membershipRepository;

    /**
     * Constructs the query service with the required repository dependency.
     *
     * @param membershipRepository membership data access layer
     */
    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * Handles the retrieval of all memberships.
     *
     * @param query the query object (empty in this case)
     * @return a list of all memberships
     */
    @Override
    public List<Memberships> handle(GetAllMemberships query) {
        return membershipRepository.findAll();
    }

    /**
     * Handles the retrieval of a specific membership by ID.
     *
     * @param query the query containing the membership ID
     * @return an Optional containing the membership if found, or empty otherwise
     */
    @Override
    public Optional<Memberships> handle(GetMembershipById query) {
        return membershipRepository.findById(query.Id());
    }
}