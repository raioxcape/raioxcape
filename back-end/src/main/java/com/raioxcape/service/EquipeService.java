package com.raioxcape.service;

import com.raioxcape.dto.creation.EquipeCreationDTO;
import com.raioxcape.exception.EntidadeJaExisteException;
import com.raioxcape.exception.EntidadeNaoExisteException;
import com.raioxcape.model.Equipe;
import com.raioxcape.repository.EquipeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;

    private final IntegranteService integranteService;

    public Equipe findByNome(String nome) {
        return this.equipeRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o nome \"%s\".", nome))
            );
    }

    public List<Equipe> findAll() {
        return this.equipeRepository.findAll();
    }

    public void save(EquipeCreationDTO equipeCreationDTO) {
        equipeCreationDTO.validate();

        if (this.equipeRepository.existsByNomeEqualsIgnoreCase(equipeCreationDTO.getNome())) {
            throw new EntidadeJaExisteException(
                String.format("Já existe uma equipe com o nome \"%s\".", equipeCreationDTO.getNome())
            );
        }

        Equipe equipe = this.equipeRepository.save(new Equipe(equipeCreationDTO.getNome()));

        for (String integrante : equipeCreationDTO.getIntegrantes()) {
            this.integranteService.save(integrante, equipe);
        }
    }

    public void deleteByNome(String nome) {
        if (!this.equipeRepository.existsByNomeEqualsIgnoreCase(nome)) {
            throw new EntidadeNaoExisteException(
                String.format("Não foi encontrada nenhuma equipe com o nome \"%s\".", nome)
            );
        }

        this.integranteService.deleteByNomeEquipe(nome);

        this.equipeRepository.deleteByNomeEqualsIgnoreCase(nome);
    }
}
