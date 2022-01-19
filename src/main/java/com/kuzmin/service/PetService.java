package com.kuzmin.service;

import com.kuzmin.domain.Pet;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kuzmin.domain.Pet}.
 */
public interface PetService {
    Pet save(Pet pet);
    Page<Pet> findAll(Pageable pageable);
    Optional<Pet> findOne(Long id);
    void delete(Long id);
}
