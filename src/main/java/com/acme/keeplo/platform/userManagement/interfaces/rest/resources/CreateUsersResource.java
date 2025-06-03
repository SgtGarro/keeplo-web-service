package com.acme.keeplo.platform.userManagement.interfaces.rest.resources;

public record CreateUsersResource(String email,
                                  String password,
                                  String name,
                                  String profilePicture) {

    public CreateUsersResource {
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
