package br.com.ada.polotech.projetotesteautomatizados.exceptions;

public class NegativeAmountException extends RuntimeException{
    public NegativeAmountException() {
        super("Quantidade não pode ser negativa");
    }
}
