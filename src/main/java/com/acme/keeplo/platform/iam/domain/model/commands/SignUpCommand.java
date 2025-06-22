package com.acme.keeplo.platform.iam.domain.model.commands;

/**
 * Sign-Up Command (SignUpCommand) for new users.

 * This command encapsulates the necessary data to register a new user in the system.
 * @param email The user's email address, which will serve as their unique identifier (username).
 * @param password The user's desired password (will be hashed by the service before saving).
 * @param name The full name of the user.
 * @param profilePicture The URL or path to the user's profile picture.
 */
public record SignUpCommand(String email,
                            String password,
                            String name,
                            String profilePicture) {

    /**
     * Validates the command inputs to ensure all required fields are present.
     *
     * @throws IllegalArgumentException if any required field (email, password, name, profilePicture) is null or blank.
     */
    public SignUpCommand {
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