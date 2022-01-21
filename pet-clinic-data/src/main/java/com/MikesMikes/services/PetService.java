package com.MikesMikes.services;

import com.MikesMikes.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> finddAll();
}
