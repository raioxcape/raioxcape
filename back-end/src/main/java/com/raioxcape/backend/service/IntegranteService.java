package com.raioxcape.backend.service;

import com.raioxcape.backend.model.Equipe;
import com.raioxcape.backend.model.Integrante;

public interface IntegranteService {

    Integrante save(String nome, Equipe equipe);
}
