package com.acme.keeplo.platform.userManagement.domain.model.commands;
/**
 * CreateUsersCommand
 * @summary
 * CreateUsersCommand is a record class that represents the command to create a User.
 */
public record CreateUsersCommand(String email,
                                 String password,
                                 String name,
                                 String profilePicture) {

    /**
     * Validates the command inputs.
     *
     * @throws IllegalArgumentException if any required field is null or blank
     */
    public CreateUsersCommand {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (profilePicture == null || profilePicture.isBlank())
            throw new IllegalArgumentException("Profile picture cannot be null or blank");

    }
}