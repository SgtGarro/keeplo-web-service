package com.acme.keeplo.platform.iam.interfaces.rest.transform;

import com.acme.keeplo.platform.iam.domain.model.commands.SignUpCommand; // Tu comando SignUpCommand
import com.acme.keeplo.platform.iam.interfaces.rest.resources.SignUpResource; // Tu recurso SignUpResource
// No necesitamos Role ni List<Role> aquí porque la asignación de roles se hace en el servicio

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        // Ahora usamos los campos de tu SignUpResource (email, password, name, profilePicture)
        return new SignUpCommand(resource.email(), resource.password(), resource.name(), resource.profilePicture());
    }
}