package com.acme.keeplo.platform.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String email, String name, String profilePicture, List<String> roles) {
}