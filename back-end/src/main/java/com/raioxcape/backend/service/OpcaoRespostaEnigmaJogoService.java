package com.raioxcape.backend.service;

import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;

import java.util.List;

public interface OpcaoRespostaEnigmaJogoService {

    OpcaoRespostaEnigmaJogo saveOpcaoRespostaEnigmaJogo(int idOpcaoRespostaEnigma, int idEnigma, int idJogo);

    List<OpcaoRespostaEnigmaJogo> saveOpcoesRespostaEnigmaJogo(List<Integer> idsOpcoesRespostaEnigma, int idEnigma, int idJogo);
}
