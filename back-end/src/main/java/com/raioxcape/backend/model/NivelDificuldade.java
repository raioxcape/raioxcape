package com.raioxcape.backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NivelDificuldade {

    FACIL   ("Fácil"),
    MEDIO   ("Médio"),
    DIFICIL ("Difícil");

    private final String nome;

    @Override
    public String toString() {
        return this.nome;
    }
}
