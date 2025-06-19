package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User; // Tu entidad User
import com.acme.keeplo.platform.iam.interfaces.rest.resources.AuthenticatedUserResource; // Tu recurso

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        // Utiliza user.getEmail() porque es tu 'username'
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), token);
    }
}