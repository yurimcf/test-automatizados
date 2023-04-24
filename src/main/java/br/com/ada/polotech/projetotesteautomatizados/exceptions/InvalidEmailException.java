package br.com.ada.polotech.projetotesteautomatizados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email inválido, tente novamento")
public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
        super("Email inválido, tente novamente");
    }
}
