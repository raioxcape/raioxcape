package com.raioxcape.backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PortaCaminho {

    CABECA_E_PESCOCO    ("Cabeça e pescoço"),
    TORAX               ("Tórax"),
    ABDOMEN             ("Abdômen"),
    MUSCULO_ESQUELETICO ("Músculo esquelético");

    private final String nome;

    @Override
    public String toString() {
        return this.nome;
    }
}
