package com.acme.keeplo.platform.subscription.interfaces.rest.transform;


import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.MembershipResource;

/**
 * Assembler class to convert Memberships entity to MembershipResource.
 */
public class MembershipResourceFromEntityAssembler {

    /**
     * Converts a Memberships entity to a MembershipResource DTO.
     *
     * @param membership the Memberships entity
     * @return the corresponding MembershipResource
     */
    public static MembershipResource toResourceFromEntity(Memberships membership) {
        return new MembershipResource(
                membership.getId(),
                membership.getName(),
                membership.getPrice(),
                membership.getDescription()
        );
    }
}