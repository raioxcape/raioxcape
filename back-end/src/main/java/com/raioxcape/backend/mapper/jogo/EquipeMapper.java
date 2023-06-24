package com.raioxcape.backend.mapper.jogo;

import com.raioxcape.backend.dto.jogo.EquipeRetrievalDTO;
import com.raioxcape.backend.mapper.equipe.IntegranteMapper;
import com.raioxcape.backend.model.Equipe;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component(value = "JogoEquipeMapper")
@RequiredArgsConstructor
public class EquipeMapper {

    private final IntegranteMapper integranteMapper;

    public EquipeRetrievalDTO toEquipeRetrievalDTO(Equipe equipe) {
        return new EquipeRetrievalDTO(
            equipe.getId(),
            equipe.getNome(),
            equipe
                .getIntegrantes()
                .stream()
                .map(this.integranteMapper::toIntegranteRetrievalDTO)
                .collect(Collectors.toList()),
            equipe.getCriadaEm(),
            equipe.getAtualizadaEm()
        );
    }
}
