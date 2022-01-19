package com.kuzmin.service;

import com.kuzmin.domain.PetType;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kuzmin.domain.PetType}.
 */
public interface PetTypeService {
    PetType save(PetType petType);
    Page<PetType> findAll(Pageable pageable);
    Optional<PetType> findOne(Long id);
    void delete(Long id);
}
