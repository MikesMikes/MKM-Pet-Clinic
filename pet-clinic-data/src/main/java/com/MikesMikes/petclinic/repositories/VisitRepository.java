package com.MikesMikes.petclinic.repositories;

import com.MikesMikes.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
