package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.aggregates.User;
import com.acme.keeplo.platform.iam.interfaces.rest.resources.UserResource;
import com.acme.keeplo.platform.iam.domain.model.entities.Role;
import java.util.stream.Collectors;
/**
 * Utility class for transforming a {@link User} entity
 * into a {@link UserResource} for REST responses.
 */
public class UserResourceFromEntityAssembler {
    /**
     * Converts a User entity into a UserResource.
     *
     * @param user the domain User object
     * @return a REST resource representing the user
     */
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).collect(Collectors.toList());
        return new UserResource(user.getId(), user.getEmail(), user.getName(), user.getProfilePicture(), roles);
    }
}