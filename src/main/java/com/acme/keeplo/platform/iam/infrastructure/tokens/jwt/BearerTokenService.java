package com.acme.keeplo.platform.iam.infrastructure.tokens.jwt;

import com.acme.keeplo.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.keeplo.platform.iam.domain.model.entities.Role;


import java.util.Set;

/**
 * This interface is a marker interface for the JWT token service.
 * It extends the {@link TokenService} interface.
 */
public interface BearerTokenService {
    String generateToken(String username);
    String generateToken(String username, Set<Role> roles);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}