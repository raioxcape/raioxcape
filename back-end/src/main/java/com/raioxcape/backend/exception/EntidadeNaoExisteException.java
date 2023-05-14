package com.raioxcape.backend.exception;

public class EntidadeNaoExisteException extends RuntimeException {

    public EntidadeNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
