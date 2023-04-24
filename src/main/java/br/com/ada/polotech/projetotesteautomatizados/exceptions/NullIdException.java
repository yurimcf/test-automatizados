package br.com.ada.polotech.projetotesteautomatizados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Este campo não pode ser nulo")
public class NullIdException extends RuntimeException{
    public NullIdException() {
        super("Este campo não pode ser nulo");
    }
}
