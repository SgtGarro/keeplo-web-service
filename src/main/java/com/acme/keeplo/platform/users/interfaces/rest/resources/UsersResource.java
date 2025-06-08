package com.acme.keeplo.platform.users.interfaces.rest.resources;

public record UsersResource(String email,
                            String password,
                            String name,
                            String profilePicture) {
}
