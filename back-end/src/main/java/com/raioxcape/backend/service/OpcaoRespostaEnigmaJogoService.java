package com.raioxcape.backend.service;

import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;

import java.util.List;

public interface OpcaoRespostaEnigmaJogoService {

    OpcaoRespostaEnigmaJogo saveOpcaoRespostaEnigmaJogo(int idOpcaoRespostaEnigma, int idEnigma, int idJogo);

    List<OpcaoRespostaEnigmaJogo> saveOpcaoRespostaEnigmaJogo(List<Integer> idsOpcoesRespostaEnigma, int idEnigma, int idJogo);
}
