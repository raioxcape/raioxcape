package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeJaExisteException;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.model.Integrante;
import com.raioxcape.backend.repository.IntegranteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegranteServiceImpl implements IntegranteService {

    private final IntegranteRepository integranteRepository;

    @Override
    public Integrante saveIntegrante(String nome, Equipe equipe) {
        if (this.integranteRepository.existsByNomeEqualsIgnoreCaseAndEquipeNomeEqualsIgnoreCase(
            nome, equipe.getNome()
        )) {
            throw new EntidadeJaExisteException(
                String.format("Já existe um integrante com o nome '%s' na equipe '%s'", nome, equipe.getNome())
            );
        }

        Integrante integrante = this.integranteRepository.save(new Integrante(nome, equipe));

        this.integranteRepository.refresh(integrante);

        return integrante;
    }
}
