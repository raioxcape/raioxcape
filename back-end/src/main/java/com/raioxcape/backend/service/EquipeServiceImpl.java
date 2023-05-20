package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
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
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;

    private final IntegranteServiceImpl integranteService;

    @Override
    public Equipe findByNome(String nome) {
        return this.equipeRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o nome igual a '%s'", nome))
            );
    }

    @Override
    public Equipe findById(int id) {
        return this.equipeRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o id igual a %d", id))
            );
    }

    @Override
    public List<Equipe> findAll() {
        List<Equipe> equipes = this.equipeRepository.findAll();

        equipes.sort(Comparator.comparing(Equipe::getCriadaEm).reversed());

        return equipes;
    }

    @Override
    public List<Jogo> findJogos(String nome) {
        List<Jogo> jogos = this.findByNome(nome).getJogos();

        jogos.sort(Comparator.comparing(Jogo::getCriadoEm).reversed());

        return jogos;
    }

    @Override
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
