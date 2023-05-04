package com.raioxcape.repository;

import com.raioxcape.model.Equipe;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EquipeRepository extends MongoRepository<Equipe, ObjectId> {

    boolean existsByNomeEqualsIgnoreCase(String nome);

    Optional<Equipe> findByNomeEqualsIgnoreCase(String nome);

    void deleteByNomeEqualsIgnoreCase(String nome);
}
