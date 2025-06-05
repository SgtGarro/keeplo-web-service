package com.acme.keeplo.platform.userManagement.domain.model.queries;

public record GetUserByIdAndEmailQuery(Long id, String email) {
    public GetUserByIdAndEmailQuery {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
