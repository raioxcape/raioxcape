package com.raioxcape.backend.service;

import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.model.EnigmaJogo;

public interface EnigmaJogoService {

    EnigmaJogo findByIdEnigmaAndIdJogo(int idEnigma, int idJogo);

    EnigmaJogo update(int idEnigma, int idJogo, EnigmaUpdateDTO enigmaUpdateDTO);
}
