package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.model.Jogo;

import java.util.List;

public interface EquipeService {

    Equipe findByNome(String nome);

    Equipe findById(int id);

    List<Equipe> findAll();

    List<Jogo> findJogos(String nome);

    Equipe save(EquipeCreationDTO equipeCreationDTO);
}
