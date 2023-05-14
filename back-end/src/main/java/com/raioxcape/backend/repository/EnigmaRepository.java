package com.raioxcape.backend.repository;

import com.raioxcape.backend.model.Enigma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnigmaRepository extends JpaRepository<Enigma, Integer>, EntityManagerRepository {

}
