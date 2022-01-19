package com.kuzmin.repository;

import com.kuzmin.domain.Visit;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class VisitRepository implements JpaRepository<Visit, Long> {
    @PersistenceContext
    private final EntityManager entityManager;

    public VisitRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Visit mergeAndSave(Visit visit) {
        visit = entityManager.merge(visit);
        return save(visit);
    }
}
