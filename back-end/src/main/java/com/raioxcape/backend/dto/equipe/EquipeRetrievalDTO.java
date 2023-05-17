package com.raioxcape.backend.dto.equipe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder(value = {"id", "nome", "integrantes", "criadaEm", "atualizadaEm"})
public class EquipeRetrievalDTO {

    private final Integer id;

    private final String nome;

    private final List<IntegranteRetrievalDTO> integrantes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadaEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadaEm;
}
