package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.EnigmaJogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnigmaJogoRepository extends JpaRepository<EnigmaJogo, Integer>, EntityManagerRepository {

    Optional<EnigmaJogo> findByEnigmaIdAndJogoId(int idEnigma, int idJogo);

    @Query(
        name = "findEnigmasJogo",
        nativeQuery = true,
        value = """
            SELECT ej.*
            FROM enigma_jogo AS ej INNER JOIN enigma AS e USING (id_enigma)
            WHERE
                ej.id_jogo = :idJogo AND
                e.porta_caminho = :portaCaminho AND
                e.nivel_dificuldade = :nivelDificuldade AND
                ej.foi_solucionado = :foiSolucionado
        """
    )
    List<EnigmaJogo> findEnigmasJogo(int idJogo, String portaCaminho, String nivelDificuldade, boolean foiSolucionado);
}
