package dev.semkoksharov.royalacademyofmakeup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UserUpdate(

        @Positive(message = "User ID should be a positive number")
        Long userToUpdateId,

        @NotBlank(message = "First name cannot be blank")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        String lastName,

        @Email(message = "Email should be valid")
        String email

) {
}
