package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.entities.Role; // Tu entidad Role
import com.acme.keeplo.platform.iam.interfaces.rest.resources.RoleResource; // Tu recurso

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}