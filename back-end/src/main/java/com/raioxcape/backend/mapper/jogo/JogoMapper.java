package com.raioxcape.backend.mapper.jogo;

import com.raioxcape.backend.dto.jogo.JogoRetrievalDTO;
import com.raioxcape.backend.mapper.equipe.EquipeMapper;
import com.raioxcape.backend.model.Jogo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JogoMapper {

    private final EquipeMapper equipeMapper;

    private final EnigmaMapper enigmaMapper;

    public JogoRetrievalDTO toJogoRetrievalDTO(Jogo jogo) {
        return new JogoRetrievalDTO(
            jogo.getId(),
            this.equipeMapper.toEquipeRetrievalDTO(jogo.getEquipe()),
            jogo.getEnigmas().stream().map(this.enigmaMapper::toEnigmaRetrievalDTO).collect(Collectors.toList()),
            jogo.getPontos(),
            jogo.getCriadoEm(),
            jogo.getAtualizadoEm()
        );
    }
}
