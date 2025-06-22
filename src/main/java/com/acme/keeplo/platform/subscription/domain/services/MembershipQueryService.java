package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetAllMemberships;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetMembershipById;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for querying membership data.
 *
 * Provides methods to retrieve all memberships or a specific membership by ID.
 */
public interface MembershipQueryService {

    /**
     * Handles the query to retrieve all memberships.
     *
     * @param query the query object representing the request
     * @return a list of all Memberships entities found
     */
    List<Memberships> handle(GetAllMemberships query);

    /**
     * Handles the query to retrieve a membership by its ID.
     *
     * @param query the query object containing the membership ID
     * @return an Optional containing the Memberships entity if found, otherwise empty
     */
    Optional<Memberships> handle(GetMembershipById query);
}