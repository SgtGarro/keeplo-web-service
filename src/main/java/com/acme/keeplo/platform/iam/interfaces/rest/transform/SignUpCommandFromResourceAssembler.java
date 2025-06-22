package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.keeplo.platform.iam.interfaces.rest.resources.SignUpResource;
/**
 * Utility class for transforming a {@link SignUpResource}
 * into a {@link SignUpCommand} used by the application layer.
 */
public class SignUpCommandFromResourceAssembler {
    /**
     * Converts a SignUpResource into a SignUpCommand.
     *
     * @param resource the resource containing sign-up data
     * @return the command with user registration information
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.email(), resource.password(), resource.name(), resource.profilePicture());
    }
}