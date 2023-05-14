package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.model.Enigma;
import com.raioxcape.backend.repository.EnigmaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnigmaService {

    private final EnigmaRepository enigmaRepository;

    public Enigma findById(Integer id) {
        return this.enigmaRepository
            .findById(id)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrado nenhum enigma com o id igual a %d", id))
            );
    }

    public List<Enigma> findAll() {
        List<Enigma> enigmas = this.enigmaRepository.findAll();

        enigmas.sort(Comparator.comparing(Enigma::getCriadoEm).reversed());

        return enigmas;
    }
}
