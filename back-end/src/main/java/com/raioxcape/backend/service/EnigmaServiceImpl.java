package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Enigma;
import com.raioxcape.backend.repository.EnigmaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnigmaServiceImpl implements EnigmaService {

    private final EnigmaRepository enigmaRepository;

    @Override
    public Enigma findEnigmaById(int id) {
        return this.enigmaRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum enigma com o id igual a %d", id))
            );
    }

    @Override
    public List<Enigma> findAllEnigmas() {
        return this.enigmaRepository.findAll();
    }
}
