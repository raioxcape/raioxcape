package com.raioxcape.exception;

public class EntidadeNaoExisteException extends RuntimeException {

    public EntidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
