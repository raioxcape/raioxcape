package com.raioxcape.backend.dto.jogo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder(value = {"id", "opcaoResposta", "estaCorreta", "explicacao", "criadaEm", "atualizadaEm"})
public class OpcaoRespostaEnigmaRetrievalDTO {

    private final Integer id;

    private final String opcaoResposta;

    private final Boolean estaCorreta;

    private final String explicacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadaEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadaEm;
}
