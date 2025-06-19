package com.acme.keeplo.platform.iam.interfaces.rest.resources;


public record SignUpResource(String email, String password, String name, String profilePicture) {
}