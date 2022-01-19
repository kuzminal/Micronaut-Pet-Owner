package com.kuzmin.service.impl;

import com.kuzmin.domain.PetType;
import com.kuzmin.repository.PetTypeRepository;
import com.kuzmin.service.PetTypeService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class PetTypeServiceImpl implements PetTypeService {
    private final Logger log =
            LoggerFactory.getLogger(PetTypeServiceImpl.class);
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType save(PetType petType) {
        log.debug("Request to save PetType : {}", petType);
        return petTypeRepository.mergeAndSave(petType);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<PetType> findAll(Pageable pageable) {
        log.debug("Request to get all PetTypes");
        return petTypeRepository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<PetType> findOne(Long id) {
        log.debug("Request to get PetType : {}", id);
        return petTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PetType : {}", id);
        petTypeRepository.deleteById(id);
    }
}
