package com.acme.keeplo.platform.userManagement.interfaces.rest.transform;

import com.acme.keeplo.platform.userManagement.domain.model.aggregates.Users;
import com.acme.keeplo.platform.userManagement.interfaces.rest.resources.UsersResource;

public class UserResourceFromEntityAssembler {
    public static UsersResource toResourceFromEntity(Users entity) {
        return new UsersResource(entity.getPassword(), entity.getName(), entity.getProfilePicture(),entity.getEmail());
    }
}
