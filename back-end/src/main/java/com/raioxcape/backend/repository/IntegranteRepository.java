package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.Integrante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegranteRepository extends JpaRepository<Integrante, Integer>, EntityManagerRepository {

    boolean existsByNomeEqualsIgnoreCaseAndEquipeNomeEqualsIgnoreCase(String nome, String nomeEquipe);
}
