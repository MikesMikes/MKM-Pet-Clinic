package com.MikesMikes.petclinic.repositories;

import com.MikesMikes.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
