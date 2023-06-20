package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeJaExisteException;
import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;
import com.raioxcape.backend.repository.OpcaoRespostaEnigmaJogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpcaoRespostaEnigmaJogoServiceImpl implements OpcaoRespostaEnigmaJogoService {

    private final OpcaoRespostaEnigmaJogoRepository opcaoRespostaEnigmaJogoRepository;

    private final OpcaoRespostaEnigmaService opcaoRespostaEnigmaService;

    private final EnigmaJogoService enigmaJogoService;

    @Autowired
    public OpcaoRespostaEnigmaJogoServiceImpl(
        OpcaoRespostaEnigmaJogoRepository opcaoRespostaEnigmaJogoRepository,
        OpcaoRespostaEnigmaService opcaoRespostaEnigmaService,
        @Lazy EnigmaJogoService enigmaJogoService
    ) {
        this.opcaoRespostaEnigmaJogoRepository = opcaoRespostaEnigmaJogoRepository;
        this.opcaoRespostaEnigmaService = opcaoRespostaEnigmaService;
        this.enigmaJogoService = enigmaJogoService;
    }

    @Override
    public OpcaoRespostaEnigmaJogo saveOpcaoRespostaEnigmaJogo(int idOpcaoRespostaEnigma, int idEnigma, int idJogo) {
        if (this.opcaoRespostaEnigmaJogoRepository.existsByOpcaoRespostaEnigmaIdAndEnigmaJogoEnigmaIdAndEnigmaJogoJogoId(
            idOpcaoRespostaEnigma,
            idEnigma,
            idJogo
        )) {
            throw new EntidadeJaExisteException(
                String.format(
                    "A opção de resposta %d já foi atribuída ao enigma %d do jogo %d",
                    idOpcaoRespostaEnigma,
                    idEnigma,
                    idJogo
                )
            );
        }

        OpcaoRespostaEnigmaJogo opcaoRespostaEnigmaJogo = this.opcaoRespostaEnigmaJogoRepository.save(
            new OpcaoRespostaEnigmaJogo(
                this.opcaoRespostaEnigmaService.findOpcaoRespostaEnigmaById(idOpcaoRespostaEnigma),
                this.enigmaJogoService.findEnigmaJogoByIdEnigmaAndIdJogo(idEnigma, idJogo)
            )
        );

        this.opcaoRespostaEnigmaJogoRepository.refresh(opcaoRespostaEnigmaJogo);

        return opcaoRespostaEnigmaJogo;
    }

    @Override
    public List<OpcaoRespostaEnigmaJogo> saveOpcaoRespostaEnigmaJogo(List<Integer> idsOpcoesRespostaEnigma, int idEnigma, int idJogo) {
        List<OpcaoRespostaEnigmaJogo> opcoesRespostaEnigmaJogo = new ArrayList<>();

        for (int idOpcaoRespostaEnigma : idsOpcoesRespostaEnigma) {
            opcoesRespostaEnigmaJogo.add(this.saveOpcaoRespostaEnigmaJogo(idOpcaoRespostaEnigma, idEnigma, idJogo));
        }

        return opcoesRespostaEnigmaJogo;
    }
}
