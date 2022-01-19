package com.kuzmin.utils;

import com.kuzmin.domain.Owner;
import com.kuzmin.domain.Pet;
import com.kuzmin.domain.Visit;
import com.kuzmin.service.OwnerService;
import com.kuzmin.service.PetService;
import com.kuzmin.service.PetTypeService;
import com.kuzmin.service.VisitService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Set;

@Singleton
public record PetOwnerCliClient(OwnerService ownerService, PetService petService,
                                VisitService visitService,
                                PetTypeService petTypeService) {
    protected Page<Owner> performFindAll() {
        return ownerService.findAll(Pageable.unpaged());
    }

    protected Owner performSave() {
        Owner owner = initOwner();
        return ownerService.save(owner);
    }

    private Owner initOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Foo");
        owner.setLastName("Bar");
        owner.setCity("Toronto");
        owner.setAddress("404 Adelaide St W");
        owner.setTelephone("647000999");
        Pet pet = new Pet();
        pet.setType(petTypeService.findAll(Pageable.unpaged()).
                getContent().get(1));
        pet.setName("Baz");
        pet.setBirthDate(LocalDate.of(2010, 12, 12));
        pet.setOwner(owner);
        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Breathing issues");
        visit.setPet(pet);
        return owner;
    }

    protected void performDelete(Owner owner) {
/** delete owner pets and their visits */
        Set<Pet> pets = owner.getPets();
        if (CollectionUtils.isNotEmpty(pets)) {
            for (Pet pet : pets) {
                Set<Visit> visits = pet.getVisits();
                if (CollectionUtils.isNotEmpty(visits)) {
                    for (Visit visit : visits) {
                        visitService.delete(visit.getId());
                    }
                }
                petService.delete(pet.getId());
            }
        }
        ownerService.delete(owner.getId());
    }

    public void performDatabaseOperations() {
        Owner owner = performSave();
        performFindAll();
        performDelete(owner);
        performFindAll();
    }
}
