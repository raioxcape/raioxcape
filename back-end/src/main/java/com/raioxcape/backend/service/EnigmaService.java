package com.raioxcape.backend.service;

import com.raioxcape.backend.model.Enigma;

import java.util.List;

public interface EnigmaService {

    Enigma findEnigmaById(int id);

    List<Enigma> findAllEnigmas();
}
