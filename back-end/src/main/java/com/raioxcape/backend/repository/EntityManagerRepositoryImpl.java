package com.raioxcape.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class EntityManagerRepositoryImpl implements EntityManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void refresh(Object object) {
        entityManager.refresh(object);
    }
}
