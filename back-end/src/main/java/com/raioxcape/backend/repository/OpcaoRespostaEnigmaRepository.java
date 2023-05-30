package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.OpcaoRespostaEnigma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcaoRespostaEnigmaRepository extends JpaRepository<OpcaoRespostaEnigma, Integer>, EntityManagerRepository {

}
