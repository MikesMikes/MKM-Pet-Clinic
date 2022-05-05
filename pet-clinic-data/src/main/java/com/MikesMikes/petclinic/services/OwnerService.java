package com.MikesMikes.petclinic.services;

import com.MikesMikes.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
