package com.raioxcape.backend.mapper.equipe;

import com.raioxcape.backend.dto.equipe.JogoRetrievalDTO;
import com.raioxcape.backend.mapper.jogo.EnigmaMapper;
import com.raioxcape.backend.model.Jogo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component(value = "EquipeJogoMapper")
@RequiredArgsConstructor
public class JogoMapper {

    private final EnigmaMapper enigmaMapper;

    public JogoRetrievalDTO toJogoRetrievalDTO(Jogo jogo) {
        return new JogoRetrievalDTO(
            jogo.getId(),
            jogo.getEnigmas().stream().map(this.enigmaMapper::toEnigmaRetrievalDTO).collect(Collectors.toList()),
            jogo.getPontos(),
            jogo.getCriadoEm(),
            jogo.getAtualizadoEm()
        );
    }
}
