package com.raioxcape.backend.mapper.jogo;

import com.raioxcape.backend.dto.jogo.retrieval.OpcaoRespostaEnigmaRetrievalDTO;
import com.raioxcape.backend.model.OpcaoRespostaEnigma;
import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpcaoRespostaEnigmaMapper {

    public OpcaoRespostaEnigmaRetrievalDTO toOpcaoRespostaEnigmaRetrievalDTO(OpcaoRespostaEnigmaJogo opcaoRespostaEnigmaJogo) {
        return new OpcaoRespostaEnigmaRetrievalDTO(
            opcaoRespostaEnigmaJogo.getId(),
            opcaoRespostaEnigmaJogo.getOpcaoRespostaEnigma().getOpcaoResposta(),
            opcaoRespostaEnigmaJogo.getOpcaoRespostaEnigma().getEstaCorreta(),
            opcaoRespostaEnigmaJogo.getOpcaoRespostaEnigma().getExplicacao(),
            opcaoRespostaEnigmaJogo.getCriadaEm(),
            opcaoRespostaEnigmaJogo.getAtualizadaEm()
        );
    }

    public OpcaoRespostaEnigmaRetrievalDTO toOpcaoRespostaEnigmaRetrievalDTO(OpcaoRespostaEnigma opcaoRespostaEnigma) {
        return new OpcaoRespostaEnigmaRetrievalDTO(
            opcaoRespostaEnigma.getId(),
            opcaoRespostaEnigma.getOpcaoResposta(),
            opcaoRespostaEnigma.getEstaCorreta(),
            opcaoRespostaEnigma.getExplicacao(),
            opcaoRespostaEnigma.getCriadaEm(),
            opcaoRespostaEnigma.getAtualizadaEm()
        );
    }
}
