package com.acme.keeplo.platform.users.interfaces.rest.transform;

import com.acme.keeplo.platform.users.domain.model.aggregates.Users;
import com.acme.keeplo.platform.users.interfaces.rest.resources.UsersResource;

public class UserResourceFromEntityAssembler {
    public static UsersResource toResourceFromEntity(Users entity) {
        return new UsersResource(entity.getPassword(), entity.getName(), entity.getProfilePicture(),entity.getEmail());
    }
}
