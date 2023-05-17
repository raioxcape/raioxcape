package com.raioxcape.backend.mapper.equipe;

import com.raioxcape.backend.dto.equipe.IntegranteRetrievalDTO;
import com.raioxcape.backend.model.Integrante;

import org.springframework.stereotype.Component;

@Component
public class IntegranteMapper {

    public IntegranteRetrievalDTO toIntegranteRetrievalDTO(Integrante integrante) {
        return new IntegranteRetrievalDTO(
            integrante.getId(),
            integrante.getNome(),
            integrante.getCriadoEm(),
            integrante.getAtualizadoEm()
        );
    }
}
