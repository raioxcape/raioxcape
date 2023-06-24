package com.raioxcape.backend.service;

import com.raioxcape.backend.model.Enigma;
import com.raioxcape.backend.model.NivelDificuldade;
import com.raioxcape.backend.model.PortaCaminho;

import java.util.List;

public interface EnigmaService {

    Enigma findEnigmaById(int id);

    List<Enigma> findAllEnigmas();

    List<Enigma> findEnigmas(PortaCaminho portaCaminho, NivelDificuldade nivelDificuldade, int quantidade);
}
