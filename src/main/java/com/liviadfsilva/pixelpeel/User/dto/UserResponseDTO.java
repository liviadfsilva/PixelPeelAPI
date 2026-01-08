package com.liviadfsilva.pixelpeel.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private final String name;
    private final String email;
}