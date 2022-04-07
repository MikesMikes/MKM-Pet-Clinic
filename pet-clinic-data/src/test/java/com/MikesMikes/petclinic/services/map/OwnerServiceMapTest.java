package com.MikesMikes.petclinic.services.map;

import com.MikesMikes.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {


    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        //size of 2
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }


    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(1l, owner.getId());
    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 6L;

        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());

    }


    @Test
    void save() {
        ownerServiceMap.save(Owner.builder().id(3L).build());
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    void delete() {
        Owner owner = Owner.builder().id(3L).build();
        ownerServiceMap.delete(owner);
        assertEquals(1, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(2L);

        assertEquals(1, ownerServiceMap.findAll().size());
    }


    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
//        assertEquals(1, ownerServiceMap.findAll().size());
        Owner smith = ownerServiceMap.findByLastName("foo");

        assertNull(smith);
    }
}