package com.MikesMikes.services;

import com.MikesMikes.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

}
