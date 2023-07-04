package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.exception.EntidadeJaExisteException;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.repository.EquipeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;

    private final IntegranteServiceImpl integranteService;

    @Override
    public Equipe findEquipeByNome(String nomeEquipe) {
        return this.equipeRepository
            .findByNomeEqualsIgnoreCase(nomeEquipe)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o nome igual a '%s'", nomeEquipe))
            );
    }

    @Override
    public Equipe findEquipeById(int idEquipe) {
        return this.equipeRepository
            .findById(idEquipe)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o id igual a %d", idEquipe))
            );
    }

    @Override
    public List<Equipe> findAllEquipes() {
        List<Equipe> equipes = this.equipeRepository.findAll();

        equipes.sort(Comparator.comparing(Equipe::getCriadaEm).reversed());

        return equipes;
    }

    @Override
    public Equipe saveEquipe(EquipeCreationDTO equipeCreationDTO) {
        equipeCreationDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeCreationDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome igual a '%s'", equipeCreationDTO.getNome())
            );
        }

        Equipe equipe = this.equipeRepository.saveAndFlush(new Equipe(equipeCreationDTO.getNome()));

        for (String nomeIntegrante : equipeCreationDTO.getIntegrantes()) {
            equipe.adicionarIntegrante(this.integranteService.saveIntegrante(nomeIntegrante, equipe));
        }

        this.equipeRepository.refresh(equipe);

        return equipe;
    }

    @Override
    public Equipe updateEquipeByNome(String nomeEquipe, EquipeUpdateDTO equipeUpdateDTO) {
        equipeUpdateDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeUpdateDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome igual a '%s'", equipeUpdateDTO.getNome())
            );
        }

        Equipe equipe = this.findEquipeByNome(nomeEquipe);

        equipe.setNome(equipeUpdateDTO.getNome());

        this.equipeRepository.saveAndFlush(equipe);

        this.equipeRepository.refresh(equipe);

        return equipe;
    }
}
