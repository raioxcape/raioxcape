package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.creation.EquipeCreationDTO;
import com.raioxcape.backend.exception.EntidadeJaExisteException;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.model.Jogo;
import com.raioxcape.backend.repository.EquipeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;

    private final IntegranteService integranteService;

    public Equipe findByNome(String nome) {
        return this.equipeRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o nome igual a '%s'", nome))
            );
    }

    public List<Equipe> findAll() {
        List<Equipe> equipes = this.equipeRepository.findAll();

        equipes.sort(Comparator.comparing(Equipe::getCriadaEm).reversed());

        return equipes;
    }

    public List<Jogo> findJogos(String nome) {
        List<Jogo> jogos = this.findByNome(nome).getJogos();

        jogos.sort(Comparator.comparing(Jogo::getCriadoEm).reversed());

        return jogos;
    }

    public Equipe save(EquipeCreationDTO equipeCreationDTO) {
        equipeCreationDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeCreationDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome igual a '%s'", equipeCreationDTO.getNome())
            );
        }

        Equipe equipe = this.equipeRepository.save(new Equipe(equipeCreationDTO.getNome()));

        for (String integrante : equipeCreationDTO.getIntegrantes()) {
            this.integranteService.save(integrante, equipe);
        }

        this.equipeRepository.refresh(equipe);

        return equipe;
    }
}
