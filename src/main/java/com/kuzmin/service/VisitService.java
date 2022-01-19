package com.kuzmin.service;

import com.kuzmin.domain.Visit;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kuzmin.domain.Visit}.
 */
public interface VisitService {
    Visit save(Visit visit);
    Page<Visit> findAll(Pageable pageable);
    Optional<Visit> findOne(Long id);
    void delete(Long id);
}
