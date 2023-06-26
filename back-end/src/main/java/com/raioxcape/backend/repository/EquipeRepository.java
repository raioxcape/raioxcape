package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.Equipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Integer>, EntityManagerRepository {

    boolean existsByNomeEqualsIgnoreCase(String nomeEquipe);

    Optional<Equipe> findByNomeEqualsIgnoreCase(String nomeEquipe);
}
