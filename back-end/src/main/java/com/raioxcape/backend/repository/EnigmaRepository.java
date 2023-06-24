package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.Enigma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnigmaRepository extends JpaRepository<Enigma, Integer>, EntityManagerRepository {

    @Query(
        name = "selectJogosEnigma",
        nativeQuery = true,
        value = """
            SELECT *
            FROM enigma
            WHERE porta_caminho = :portaCaminho AND nivel_dificuldade = :nivelDificuldade
            ORDER BY RAND()
            LIMIT :quantidade
        """
    )
    List<Enigma> findEnigmas(String portaCaminho, String nivelDificuldade, int quantidade);
}
