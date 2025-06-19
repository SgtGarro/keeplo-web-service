package com.acme.keeplo.platform.iam.domain.services;

import com.acme.keeplo.platform.iam.domain.model.entities.Role;
import com.acme.keeplo.platform.iam.domain.model.commands.SeedRolesCommand; // Lo crearemos después, si el profesor lo tiene

import java.util.Optional;

/**
 * Command service interface for role management.
 * Defines operations to modify or initialize roles.
 */
public interface RoleCommandService {
    /**
     * Handles the command to initialize default roles in the system.
     * This is typically used once at application startup.
     * @param command The command to initialize roles.
     */
    void handle(SeedRolesCommand command);
}