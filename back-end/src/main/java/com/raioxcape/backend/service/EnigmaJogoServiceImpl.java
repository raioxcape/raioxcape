package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.EnigmaJogo;
import com.raioxcape.backend.repository.EnigmaJogoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnigmaJogoServiceImpl implements EnigmaJogoService {

    private final EnigmaJogoRepository enigmaJogoRepository;

    private final OpcaoRespostaEnigmaJogoService opcaoRespostaEnigmaJogoService;

    @Override
    public EnigmaJogo findEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo) {
        return this.enigmaJogoRepository
            .findByEnigmaIdAndJogoId(idEnigma, idJogo)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma relação entre o enigma %d e o jogo %d", idEnigma, idJogo))
            );
    }

    @Override
    public EnigmaJogo updateEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo, EnigmaUpdateDTO enigmaUpdateDTO) {
        enigmaUpdateDTO.validate();

        EnigmaJogo enigmaJogoAntigo = this.findEnigmaJogoByIdEnigmaAndIdJogo(idEnigma, idJogo);

        int tempoDecorridoSolucaoSegundos = enigmaUpdateDTO.getTempoDecorridoSolucaoSegundos();
        int pontosEquipe = enigmaJogoAntigo.getEnigma().calcularPontosEquipe(enigmaUpdateDTO.getIdsOpcoesRespostaEquipe(), tempoDecorridoSolucaoSegundos);

        enigmaJogoAntigo.setTempoDecorridoSolucaoSegundos(tempoDecorridoSolucaoSegundos);
        enigmaJogoAntigo.setPontos(pontosEquipe);

        this.opcaoRespostaEnigmaJogoService.saveOpcaoRespostaEnigmaJogo(enigmaUpdateDTO.getIdsOpcoesRespostaEquipe(), idEnigma, idJogo);

        EnigmaJogo enigmaJogoNovo = this.enigmaJogoRepository.save(enigmaJogoAntigo);

        this.enigmaJogoRepository.refresh(enigmaJogoNovo);

        return enigmaJogoNovo;
    }
}
