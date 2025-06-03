package com.acme.keeplo.platform.userManagement.domain.model.queries;
/**
 * @summary
 * This class represents the query to get a user by its id.
 * @param id - the id of the user
 */
public record GetAllUsersByIdQuery(Long id) {
    public GetAllUsersByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
