package br.com.ada.polotech.projetotesteautomatizados.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class EmailValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public boolean validate(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
