package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.dto.jogo.JogoCreationDTO;
import com.raioxcape.backend.model.Jogo;

import java.util.List;

public interface JogoService {

    Jogo findJogoById(int id);

    List<Jogo> findAllJogos();

    Jogo saveJogo(JogoCreationDTO jogoCreationDTO);

    Jogo updateJogoEnigmaByIdJogoAndIdEnigma(int idJogo, int idEnigma, EnigmaUpdateDTO enigmaUpdateDTO);
}
