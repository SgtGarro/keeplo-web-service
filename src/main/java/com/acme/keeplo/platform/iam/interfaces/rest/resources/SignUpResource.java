package com.acme.keeplo.platform.iam.interfaces.rest.resources;


/**
 * Resource representing the data required for registering a new user.
 *
 * @param email the email address of the user
 * @param password the password chosen by the user
 * @param name the full name of the user
 * @param profilePicture the URL or path to the user's profile picture
 */
public record SignUpResource(String email, String password, String name, String profilePicture) {
}