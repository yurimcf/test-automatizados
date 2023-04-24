package br.com.ada.polotech.projetotesteautomatizados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "balance insufficient!")
public class InsufficientValueException extends RuntimeException {

    public InsufficientValueException() {
        super("Saldo Insuficiente");
    }
}
