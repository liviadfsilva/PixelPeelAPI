package com.liviadfsilva.pixelpeel.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.liviadfsilva.pixelpeel.Auth.dto.AuthenticatedUserDTO;

@Component("authz")
public class AuthorisationService {
    public boolean isCurrentUser(Long userId) {
        AuthenticatedUserDTO user =
            (AuthenticatedUserDTO) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return user.getId().equals(userId);
    }
}
