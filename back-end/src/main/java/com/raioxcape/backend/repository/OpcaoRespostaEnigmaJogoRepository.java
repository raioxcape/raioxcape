package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.OpcaoRespostaEnigmaJogo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcaoRespostaEnigmaJogoRepository extends
    JpaRepository<OpcaoRespostaEnigmaJogo, Integer>,
    EntityManagerRepository
{

    boolean existsByOpcaoRespostaEnigmaIdAndEnigmaJogoEnigmaIdAndEnigmaJogoJogoId(
        int idOpcaoRespostaEnigma,
        int idEnigma,
        int idJogo
    );
}
