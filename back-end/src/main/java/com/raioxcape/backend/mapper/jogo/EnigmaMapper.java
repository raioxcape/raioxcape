package com.raioxcape.backend.mapper.jogo;

import com.raioxcape.backend.dto.jogo.EnigmaRetrievalDTO;
import com.raioxcape.backend.model.EnigmaJogo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component(value = "JogoEnigmaMapper")
@RequiredArgsConstructor
public class EnigmaMapper {

    private final OpcaoRespostaEnigmaMapper opcaoRespostaEnigmaMapper;

    public EnigmaRetrievalDTO toEnigmaRetrievalDTO(EnigmaJogo enigmaJogo) {
        return new EnigmaRetrievalDTO(
            enigmaJogo.getEnigma().getId(),
            enigmaJogo.getEnigma().getPergunta(),
            enigmaJogo
                .getEnigma()
                .getOpcoesResposta()
                .stream()
                .map(this.opcaoRespostaEnigmaMapper::toOpcaoRespostaEnigmaRetrievalDTO)
                .collect(Collectors.toList()),
            enigmaJogo.getEnigma().getPortaCaminho().name(),
            enigmaJogo.getEnigma().getNivelDificuldade().name(),
            enigmaJogo.getEnigma().getTempoEstimadoSolucaoSegundos(),
            enigmaJogo.getEnigma().getPontos(),
            enigmaJogo.getFoiSolucionado(),
            enigmaJogo
                .getRespostas()
                .stream()
                .map(this.opcaoRespostaEnigmaMapper::toOpcaoRespostaEnigmaRetrievalDTO)
                .collect(Collectors.toList()),
            enigmaJogo.getTempoDecorridoSolucaoSegundos(),
            enigmaJogo.getPontos(),
            enigmaJogo.getEnigma().getCriadoEm(),
            enigmaJogo.getAtualizadoEm()
        );
    }
}
