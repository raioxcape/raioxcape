package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.exception.EntidadeJaExisteException;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.repository.EquipeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;

    private final IntegranteServiceImpl integranteService;

    @Override
    public Equipe findEquipeById(int id) {
        return this.equipeRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o id igual a %d", id))
            );
    }

    @Override
    public List<Equipe> findAllEquipes() {
        List<Equipe> equipes = this.equipeRepository.findAll();

        equipes.sort(Comparator.comparing(Equipe::getCriadaEm).reversed());

        return equipes;
    }

    @Transactional
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

    @Transactional
    @Override
    public Equipe updateEquipeById(int id, EquipeUpdateDTO equipeUpdateDTO) {
        equipeUpdateDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeUpdateDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome igual a '%s'", equipeUpdateDTO.getNome())
            );
        }

        Equipe equipe = this.findEquipeById(id);

        equipe.setNome(equipeUpdateDTO.getNome());

        this.equipeRepository.saveAndFlush(equipe);

        this.equipeRepository.refresh(equipe);

        return equipe;
    }
}
