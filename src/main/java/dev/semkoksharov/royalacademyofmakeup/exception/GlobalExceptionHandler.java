package dev.semkoksharov.royalacademyofmakeup.exception;


import dev.semkoksharov.royalacademyofmakeup.dto.ErrorResponse;
import dev.semkoksharov.royalacademyofmakeup.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errorMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> String.format(
                        "[%s]: %s", ((FieldError) error).getField(), error.getDefaultMessage()
                ))
                .collect(Collectors.joining(",\n"));

        return new ValidationError(HttpStatus.BAD_REQUEST.toString(), errorMessages, LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGlobalException(Exception ex) {

        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }


    private ErrorResponse generateErrorResponse(HttpStatus status, Exception ex) {

        return new ErrorResponse(
                status.toString(),
                LocalDateTime.now(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                Arrays.toString(ex.getStackTrace())
        );
    }

}

