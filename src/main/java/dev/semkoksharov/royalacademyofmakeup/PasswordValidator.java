package dev.semkoksharov.royalacademyofmakeup;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;



public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final
    Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext
            context) {
        return password != null
                && PASSWORD_PATTERN.matcher(password).matches();
    }
}
