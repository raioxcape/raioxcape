package com.raioxcape.backend.service;

import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;

import java.util.List;

public interface OpcaoRespostaEnigmaJogoService {

    OpcaoRespostaEnigmaJogo save(int idOpcaoRespostaEnigma, int idEnigma, int idJogo);

    List<OpcaoRespostaEnigmaJogo> save(List<Integer> idsOpcoesRespostaEnigma, int idEnigma, int idJogo);
}
