package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.model.Equipe;

import java.util.List;

public interface EquipeService {

    Equipe findEquipeById(int id);

    List<Equipe> findAllEquipes();

    Equipe saveEquipe(EquipeCreationDTO equipeCreationDTO);

    Equipe updateEquipeById(int id, EquipeUpdateDTO equipeUpdateDTO);
}
