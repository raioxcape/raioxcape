package com.raioxcape.backend.service;

import com.raioxcape.backend.model.Jogo;

import java.util.List;

public interface JogoService {

    Jogo findById(int id);

    List<Jogo> findAll();
}
