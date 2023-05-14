package com.raioxcape.backend.exception;

public class EntidadeJaExisteException extends RuntimeException {

    public EntidadeJaExisteException(String mensagem) {
        super(mensagem);
    }
}
