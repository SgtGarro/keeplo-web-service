package com.acme.keeplo.platform.iam.domain.model.queries;

public record GetUserByEmailQuery(String email) {
    public GetUserByEmailQuery {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
    }
}
