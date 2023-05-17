package com.raioxcape.backend.mapper.enigma;

import com.raioxcape.backend.dto.enigma.EnigmaRetrievalDTO;
import com.raioxcape.backend.mapper.jogo.OpcaoRespostaEnigmaMapper;
import com.raioxcape.backend.model.Enigma;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnigmaMapper {

    private final OpcaoRespostaEnigmaMapper opcaoRespostaEnigmaMapper;

    public EnigmaRetrievalDTO toEnigmaRetrievalDTO(Enigma enigma) {
        return new EnigmaRetrievalDTO(
            enigma.getId(),
            enigma.getPergunta(),
            enigma
                .getOpcoesResposta()
                .stream()
                .map(this.opcaoRespostaEnigmaMapper::toOpcaoRespostaEnigmaRetrievalDTO)
                .collect(Collectors.toList()),
            enigma.getPortaCaminho().name(),
            enigma.getNivelDificuldade().name(),
            enigma.getTempoEstimadoSolucaoSegundos(),
            enigma.getPontos(),
            enigma.getCriadoEm(),
            enigma.getAtualizadoEm()
        );
    }
}
