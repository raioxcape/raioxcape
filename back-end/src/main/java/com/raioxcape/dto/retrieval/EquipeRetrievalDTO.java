package com.raioxcape.dto.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import java.time.LocalDateTime;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder(value = {"id", "nome", "integrantes", "criadaEm", "atualizadaEm"})
public class EquipeRetrievalDTO {

    private final Integer id;

    private final String nome;

    private final Set<String> integrantes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadaEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadaEm;
}
