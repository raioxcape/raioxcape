package com.raioxcape.service;

import com.raioxcape.exception.EntidadeJaExisteException;
import com.raioxcape.model.Equipe;
import com.raioxcape.model.Integrante;
import com.raioxcape.repository.IntegranteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegranteService {

    private final IntegranteRepository integranteRepository;

    public void save(String nome, Equipe equipe) {
        if (this.integranteRepository.existsByNomeEqualsIgnoreCaseAndEquipeNomeEqualsIgnoreCase(
            nome, equipe.getNome()
        )) {
            throw new EntidadeJaExisteException(
                String.format(
                    "Já existe um integrante com o nome \"%s\" na equipe \"%s\".",
                    nome,
                    equipe.getNome()
                )
            );
        }

        this.integranteRepository.save(new Integrante(nome, equipe));
    }

    public void deleteByNomeEquipe(String nomeEquipe) {
        this.integranteRepository.deleteByEquipeNomeEqualsIgnoreCase(nomeEquipe);
    }
}
