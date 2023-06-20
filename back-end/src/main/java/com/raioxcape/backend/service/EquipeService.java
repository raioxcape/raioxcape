package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.model.Jogo;

import java.util.List;

public interface EquipeService {

    Equipe findEquipeByNome(String nome);

    Equipe findEquipeById(int id);

    List<Equipe> findAllEquipes();

    List<Jogo> findAllJogos(String nome);

    Equipe saveEquipe(EquipeCreationDTO equipeCreationDTO);

    Equipe updateEquipeByNome(String nome, EquipeUpdateDTO equipeUpdateDTO);
}
