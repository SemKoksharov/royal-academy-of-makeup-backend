package dev.semkoksharov.royalacademyofmakeup.dto;

import dev.semkoksharov.royalacademyofmakeup.model.Role;

public record UserResponse(
        Long id,
        String login,
        String firstName,
        String lastName,
        String email,
        Role role
) {}
