package com.MikesMikes.petclinic.services;

import com.MikesMikes.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

}
