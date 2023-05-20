package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.JogoCreationDTO;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Jogo;
import com.raioxcape.backend.repository.JogoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoServiceImpl implements JogoService {

    private final JogoRepository jogoRepository;

    private final EquipeService equipeService;

    @Override
    public Jogo findById(int id) {
        return this.jogoRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum jogo com o id igual %d", id))
            );
    }

    @Override
    public List<Jogo> findAll() {
        List<Jogo> jogos = this.jogoRepository.findAll();

        jogos.sort(Comparator.comparing(Jogo::getCriadoEm).reversed());

        return jogos;
    }

    @Override
    public Jogo save(JogoCreationDTO jogoCreationDTO) {
        jogoCreationDTO.validate();

        Jogo jogo = this.jogoRepository.save(new Jogo(this.equipeService.findById(jogoCreationDTO.getIdEquipe())));

        this.jogoRepository.refresh(jogo);

        return jogo;
    }
}
