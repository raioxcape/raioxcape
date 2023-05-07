package com.raioxcape.repository;

import com.raioxcape.model.Integrante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface IntegranteRepository extends JpaRepository<Integrante, Integer> {

    boolean existsByNomeEqualsIgnoreCaseAndEquipeNomeEqualsIgnoreCase(String nome, String nomeEquipe);

    @Modifying
    @Transactional
    void deleteByEquipeNomeEqualsIgnoreCase(String nomeEquipe);
}
