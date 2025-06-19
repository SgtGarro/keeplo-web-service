package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User; // Tu entidad User
import com.acme.keeplo.platform.iam.interfaces.rest.resources.UserResource; // Tu recurso UserResource
import com.acme.keeplo.platform.iam.domain.model.entities.Role; // Tu entidad Role
import java.util.stream.Collectors; // Necesario para .collect(Collectors.toList())

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).collect(Collectors.toList()); // Collect para toList()
        // Usamos user.getEmail() porque es tu 'username'
        // Añadimos name y profilePicture
        return new UserResource(user.getId(), user.getEmail(), user.getName(), user.getProfilePicture(), roles);
    }
}