package com.acme.keeplo.platform.subscription.interfaces.rest.transform;


import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.MembershipResource;

public class MembershipResourceFromEntityAssembler {
    public static MembershipResource toResourceFromEntity(Memberships membership) {
        return new MembershipResource(
                membership.getId(),
                membership.getName(),
                membership.getPrice(),
                membership.getDescription()
        );
    }
}