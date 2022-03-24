package com.MikesMikes.petclinic.repositories;

import com.MikesMikes.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
