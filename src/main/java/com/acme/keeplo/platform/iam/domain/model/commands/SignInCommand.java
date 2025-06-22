package com.acme.keeplo.platform.iam.domain.model.commands;

/**
 * Sign-in command (SignInCommand).
 * This command encapsulates the credentials a user provides to authenticate with the system.
 *
 * @param username The username of the user attempting to sign in (in our case, the email).
 * @param password The password provided by the user.
 */

public record SignInCommand(String username, String password) {
    public SignInCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}