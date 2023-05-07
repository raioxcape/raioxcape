package com.raioxcape.mapper;

import com.raioxcape.dto.retrieval.EquipeRetrievalDTO;
import com.raioxcape.model.Equipe;
import com.raioxcape.model.Integrante;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EquipeMapper {

    public EquipeRetrievalDTO toEquipeRetrievalDTO(Equipe equipe) {
        return new EquipeRetrievalDTO(
            equipe.getId(),
            equipe.getNome(),
            equipe.getIntegrantes().stream().map(Integrante::getNome).collect(Collectors.toSet()),
            equipe.getCriadaEm(),
            equipe.getAtualizadaEm()
        );
    }
}
