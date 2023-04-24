package br.com.ada.polotech.projetotesteautomatizados.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private final EmailValidator validadorEmail = new EmailValidator();
    String email;
    boolean response;

    @Test
    void testEmailValid() {
        // Given
        email = "yuri@gmail.com";

        // When
        response = validadorEmail.validate(email);

        // Then
        assertTrue(response, "Esse é um email valido e nosso validador deve confirmar isso");
    }


    @Test
    void testEmailNoAtSign() {
        email = "yurigmail.com";

        // When
        response = validadorEmail.validate(email);

        // Then
        assertFalse(response, "Esse NÃO é um email valido, pois não possui @.");
    }

    @Test
    void testEmailSemAPrimeiraValidacao() {
        email = "@gmail.com";

        // When =
        response = validadorEmail.validate(email);

        // Then
        assertFalse(response, "Esse NÃO é um email valido, pois não possui nada antes @.");
    }

    @Test
    void testEmailNull() {

        // When =
        response = validadorEmail.validate(null);

        // Then
        assertFalse(response, "Email não pode ser null");
    }

    @Test
    void testEmailNoPoint() {
        email = "yuri@gmailcom";

        // When =
        response = validadorEmail.validate(email);

        // Then
        assertFalse(response, "Esse NÃO é um email valido, pois não possui um ponto depois do dominio");
    }

    @Test
    void testEmailWithIncompleteDomain() {
        email = "yuri@.com";

        response = validadorEmail.validate(email);

        assertFalse(response, "É necesseario ter um Dóminio");
    }

}