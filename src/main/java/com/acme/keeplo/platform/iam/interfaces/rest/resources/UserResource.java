package com.acme.keeplo.platform.iam.interfaces.rest.resources;

import java.util.List;
/**
 * Resource representing a user's public information.
 *
 * @param id the unique identifier of the user
 * @param email the email address of the user
 * @param name the full name of the user
 * @param profilePicture the URL or path to the user's profile picture
 * @param roles the list of role names assigned to the user
 */
public record UserResource(Long id, String email, String name, String profilePicture, List<String> roles) {
}