package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.Jogo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Integer>, EntityManagerRepository {

}
