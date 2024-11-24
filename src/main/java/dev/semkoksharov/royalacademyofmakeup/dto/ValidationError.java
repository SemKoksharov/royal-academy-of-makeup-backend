package dev.semkoksharov.royalacademyofmakeup.dto;

import java.time.LocalDateTime;

public record ValidationError(
        String status,
        String messages,
        LocalDateTime timestamp
) {
}
