package com.acme.keeplo.platform.users.interfaces.rest.transform;

import com.acme.keeplo.platform.users.domain.model.commands.CreateUsersCommand;

import com.acme.keeplo.platform.users.interfaces.rest.resources.CreateUsersResource;



/**
 * Assembler to create a CreateUSerCommand from a CreateUserResource.
 * @since 1.0
 */
public class CreateUsersCommandFromResourceAssembler {
    /**
     * Converts a CreateUserResource to a CreateUserCommand.
     * @param resource CreateUSerResource to convert
     * @return CreateUserCommand created from the resource
     */
    public static CreateUsersCommand toCommandFromResource(CreateUsersResource resource) {
        return new CreateUsersCommand(resource.email(), resource.name(), resource.password(), resource.profilePicture());
    }

}
