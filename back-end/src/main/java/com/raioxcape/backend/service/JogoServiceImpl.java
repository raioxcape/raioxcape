package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.dto.jogo.JogoCreationDTO;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.*;
import com.raioxcape.backend.repository.JogoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoServiceImpl implements JogoService {

    private final JogoRepository jogoRepository;

    private final EquipeService equipeService;

    private final EnigmaJogoService enigmaJogoService;

    @Value(value = "${jogo.quantidade-enigmas-por-porta-caminho}")
    private int quantidadeEnigmasPorPortaCaminho;

    @Override
    public Jogo findJogoById(int id) {
        return this.jogoRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum jogo com o id igual %d", id))
            );
    }

    @Override
    public List<Jogo> findAllJogos() {
        List<Jogo> jogos = this.jogoRepository.findAll();

        jogos.sort(Comparator.comparing(Jogo::getCriadoEm).reversed());

        return jogos;
    }

    @Transactional
    @Override
    public Jogo saveJogo(JogoCreationDTO jogoCreationDTO) {
        jogoCreationDTO.validate();

        Jogo jogo = this.jogoRepository.saveAndFlush(new Jogo(this.equipeService.findEquipeById(jogoCreationDTO.getIdEquipe())));

        jogo.adicionarEnigmas(this.enigmaJogoService.selectEnigmasJogo(jogo, this.quantidadeEnigmasPorPortaCaminho));

        this.jogoRepository.refresh(jogo);

        return jogo;
    }

    @Transactional
    @Override
    public Jogo updateEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo, EnigmaUpdateDTO enigmaUpdateDTO) {
        Jogo jogo = this.enigmaJogoService.updateEnigmaJogoByIdEnigmaAndIdJogo(idEnigma, idJogo, enigmaUpdateDTO).getJogo();

        this.jogoRepository.refresh(jogo);

        return jogo;
    }
}
