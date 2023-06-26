package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.model.Equipe;

import java.util.List;

public interface EquipeService {

    Equipe findEquipeByNome(String nomeEquipe);

    Equipe findEquipeById(int idEquipe);

    List<Equipe> findAllEquipes();

    Equipe saveEquipe(EquipeCreationDTO equipeCreationDTO);

    Equipe updateEquipeByNome(String nomeEquipe, EquipeUpdateDTO equipeUpdateDTO);
}
