package dev.semkoksharov.royalacademyofmakeup.dto;

import dev.semkoksharov.royalacademyofmakeup.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ChangePasswordRequest(

        @Positive(message = "User ID should be a positive number")
        Long userId,

        @NotBlank(message = "Old password cannot be blank")
        @NotEmpty(message = "Old password cannot be empty")
        String oldPassWord,

        @ValidPassword // there is the default message in the validator
        String newPassWord
) {
}
