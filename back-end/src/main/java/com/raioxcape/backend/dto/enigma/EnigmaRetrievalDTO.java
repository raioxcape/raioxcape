package com.raioxcape.backend.dto.enigma;

import com.raioxcape.backend.dto.jogo.OpcaoRespostaEnigmaRetrievalDTO;

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
@JsonPropertyOrder(value = {
    "id", "pergunta", "opcoesResposta", "portaCaminho", "nivelDificuldade",
    "tempoEstimadoSolucaoSegundos", "pontos", "criadoEm", "atualizadoEm"
})
public class EnigmaRetrievalDTO {

    private final Integer id;

    private final String pergunta;

    private final List<OpcaoRespostaEnigmaRetrievalDTO> opcoesResposta;

    private final String portaCaminho;

    private final String nivelDificuldade;

    private Integer tempoEstimadoSolucaoSegundos;

    private Integer pontos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadoEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadoEm;
}
