package com.raioxcape.backend.mapper.equipe;

import com.raioxcape.backend.dto.equipe.EquipeRetrievalDTO;
import com.raioxcape.backend.model.Equipe;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipeMapper {

    private final IntegranteMapper integranteMapper;

    private final JogoMapper jogoMapper;

    public EquipeRetrievalDTO toEquipeRetrievalDTO(Equipe equipe) {
        return new EquipeRetrievalDTO(
            equipe.getId(),
            equipe.getNome(),
            equipe
                .getIntegrantes()
                .stream()
                .map(this.integranteMapper::toIntegranteRetrievalDTO)
                .collect(Collectors.toList()),
            equipe
                .getJogos()
                .stream()
                .map(this.jogoMapper::toJogoRetrievalDTO)
                .collect(Collectors.toList()),
            equipe.getCriadaEm(),
            equipe.getAtualizadaEm()
        );
    }
}
