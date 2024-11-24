package dev.semkoksharov.royalacademyofmakeup.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String errorCode,
        LocalDateTime timestamp,
        String exception,
        String message,
        String stackTrace
) {
}
