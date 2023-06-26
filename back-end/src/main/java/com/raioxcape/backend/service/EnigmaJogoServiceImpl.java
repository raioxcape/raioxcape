package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.exception.ValorInvalidoException;
import com.raioxcape.backend.model.*;
import com.raioxcape.backend.repository.EnigmaJogoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnigmaJogoServiceImpl implements EnigmaJogoService {

    private final EnigmaJogoRepository enigmaJogoRepository;

    private final OpcaoRespostaEnigmaJogoService opcaoRespostaEnigmaJogoService;

    private final EnigmaService enigmaService;

    @Override
    public EnigmaJogo findEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo) {
        return this.enigmaJogoRepository
            .findByEnigmaIdAndJogoId(idEnigma, idJogo)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma relação entre o enigma %d e o jogo %d", idEnigma, idJogo))
            );
    }

    @Transactional
    @Override
    public EnigmaJogo updateEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo, EnigmaUpdateDTO enigmaUpdateDTO) {
        enigmaUpdateDTO.validate();

        EnigmaJogo antigoEnigmaJogo = this.findEnigmaJogoByIdEnigmaAndIdJogo(idEnigma, idJogo);

        antigoEnigmaJogo.update(enigmaUpdateDTO.getIdsOpcoesRespostaEquipe(), enigmaUpdateDTO.getTempoDecorridoSolucaoSegundos());

        antigoEnigmaJogo.adicionarRespostas(this.opcaoRespostaEnigmaJogoService.saveOpcoesRespostaEnigmaJogo(enigmaUpdateDTO.getIdsOpcoesRespostaEquipe(), idEnigma, idJogo));

        EnigmaJogo novoEnigmaJogo = this.enigmaJogoRepository.saveAndFlush(antigoEnigmaJogo);

        this.enigmaJogoRepository.refresh(novoEnigmaJogo);

        return novoEnigmaJogo;
    }

    @Transactional
    @Override
    public List<EnigmaJogo> selectEnigmasJogo(Jogo jogo, int quantidade) {
        if (Objects.isNull(jogo)) {
            throw new ValorInvalidoException("O jogo deve ser informado");
        }

        List<EnigmaJogo> enigmasJogo = new ArrayList<>();

        for (PortaCaminho portaCaminho : PortaCaminho.values()) {
            for (NivelDificuldade nivelDificuldade : NivelDificuldade.values()) {
                List<Enigma> enigmas = this.enigmaService.findEnigmas(portaCaminho, nivelDificuldade, quantidade);

                for (Enigma enigma : enigmas) {
                    enigmasJogo.add(this.enigmaJogoRepository.saveAndFlush(new EnigmaJogo(enigma, jogo)));
                }
            }
        }

        return enigmasJogo;
    }

    @Override
    public List<EnigmaJogo> findEnigmasJogo(
        int idJogo,
        String portaCaminho,
        String nivelDificuldade,
        boolean foiSolucionado
    ) {
        return this.enigmaJogoRepository.findEnigmasJogo(idJogo, portaCaminho, nivelDificuldade, foiSolucionado);
    }
}
