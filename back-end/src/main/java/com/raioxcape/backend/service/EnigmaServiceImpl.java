package com.raioxcape.backend.service;

import com.raioxcape.backend.exception.EntidadeNaoExisteException;
import com.raioxcape.backend.exception.ValorInvalidoException;
import com.raioxcape.backend.model.Enigma;
import com.raioxcape.backend.model.NivelDificuldade;
import com.raioxcape.backend.model.PortaCaminho;
import com.raioxcape.backend.repository.EnigmaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<Enigma> findEnigmas(PortaCaminho portaCaminho, NivelDificuldade nivelDificuldade, int quantidade) {
        if (Objects.isNull(portaCaminho)) {
            throw new ValorInvalidoException("A porta-caminho deve ser informada");
        }

        if (Objects.isNull(nivelDificuldade)) {
            throw new ValorInvalidoException("O nível de dificuldade deve ser informado");
        }

        if (quantidade < 1) {
            throw new ValorInvalidoException("A quantidade de enigmas desejados deve ser informada");
        }

        List<Enigma> enigmas = this.enigmaRepository.findEnigmas(portaCaminho.name(), nivelDificuldade.name(), quantidade);

        if (Objects.isNull(enigmas) || enigmas.isEmpty()) {
            throw new ValorInvalidoException("Não foram encontrados enigmas com as características informadas");
        }

        Collections.shuffle(enigmas);

        return enigmas;
    }
}
