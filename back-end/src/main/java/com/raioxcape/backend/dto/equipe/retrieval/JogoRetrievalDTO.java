package com.raioxcape.backend.dto.equipe.retrieval;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.raioxcape.backend.dto.jogo.retrieval.EnigmaRetrievalDTO;

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
@JsonPropertyOrder(value = {"id", "enigmas", "pontos", "criadoEm", "atualizadoEm"})
public class JogoRetrievalDTO {

    private final Integer id;

    private final List<EnigmaRetrievalDTO> enigmas;

    private final Integer pontos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadoEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadoEm;
}
