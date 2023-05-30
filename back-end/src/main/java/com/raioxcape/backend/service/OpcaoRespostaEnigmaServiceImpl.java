package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.OpcaoRespostaEnigma;
import com.raioxcape.backend.repository.OpcaoRespostaEnigmaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpcaoRespostaEnigmaServiceImpl implements OpcaoRespostaEnigmaService {

    private final OpcaoRespostaEnigmaRepository opcaoRespostaEnigmaRepository;

    @Override
    public OpcaoRespostaEnigma findById(int id) {
        return this.opcaoRespostaEnigmaRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma opção de resposta de enigma com o id igual a %d", id))
            );
    }
}
