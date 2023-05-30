package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.EnigmaJogo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnigmaJogoRepository extends JpaRepository<EnigmaJogo, Integer>, EntityManagerRepository {

    Optional<EnigmaJogo> findByEnigmaIdAndJogoId(int idEnigma, int idJogo);
}
