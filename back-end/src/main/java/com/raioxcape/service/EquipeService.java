package com.raioxcape.service;

import com.raioxcape.dto.creation.EquipeCreationDTO;
import com.raioxcape.exception.EntidadeJaExisteException;
import com.raioxcape.exception.EntidadeNaoEncontradaException;
import com.raioxcape.model.Equipe;
import com.raioxcape.repository.EquipeRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;

    public Equipe findByNome(String nome) {
        return this.equipeRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não foi encontrada nenhuma equipe com o nome \"%s\".", nome))
            );
    }

    public void save(EquipeCreationDTO equipeCreationDTO) {
        equipeCreationDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeCreationDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome \"%s\".", equipeCreationDTO.getNome())
            );
        }

        this.equipeRepository.save(new Equipe(equipeCreationDTO.getNome(), equipeCreationDTO.getIntegrantes()));
    }
}
