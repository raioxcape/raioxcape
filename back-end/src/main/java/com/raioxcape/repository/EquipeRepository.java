package com.raioxcape.repository;

import com.raioxcape.model.Equipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    boolean existsByNomeEqualsIgnoreCase(String nome);

    Optional<Equipe> findByNomeEqualsIgnoreCase(String nome);

    @Modifying
    @Transactional
    void deleteByNomeEqualsIgnoreCase(String nome);
}
