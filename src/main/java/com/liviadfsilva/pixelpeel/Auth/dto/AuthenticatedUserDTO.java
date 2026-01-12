package com.liviadfsilva.pixelpeel.Auth.dto;

import lombok.Getter;

@Getter
public class AuthenticatedUserDTO {
    private final Long id;
    private final String email;

    public AuthenticatedUserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
