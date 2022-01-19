package com.kuzmin.service;

import com.kuzmin.domain.Owner;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.kuzmin.domain.Owner}.
 */
public interface OwnerService {
    Owner save(Owner owner);
    Page<Owner> findAll(Pageable pageable);
    Optional<Owner> findOne(Long id);
    void delete(Long id);
}
