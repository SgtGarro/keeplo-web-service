package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.commands.SignInCommand; // Tu comando SignInCommand
import com.acme.keeplo.platform.iam.interfaces.rest.resources.SignInResource; // Tu recurso SignInResource

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        // Utiliza signInResource.email() porque es tu 'username'
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}