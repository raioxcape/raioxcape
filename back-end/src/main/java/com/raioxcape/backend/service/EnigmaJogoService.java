package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.model.EnigmaJogo;
import com.raioxcape.backend.model.Jogo;

import java.util.List;

public interface EnigmaJogoService {

    EnigmaJogo findEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo);

    EnigmaJogo updateEnigmaJogoByIdEnigmaAndIdJogo(int idEnigma, int idJogo, EnigmaUpdateDTO enigmaUpdateDTO);

    List<EnigmaJogo> selectEnigmasJogo(Jogo jogo, int quantidade);
}
