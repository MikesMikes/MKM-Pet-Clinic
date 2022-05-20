package com.MikesMikes.petclinic.controllers;

import com.MikesMikes.petclinic.model.Owner;
import com.MikesMikes.petclinic.services.OwnerService;
import com.MikesMikes.petclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    MockMvc mockMvc;

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController petController;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().id(1L).build();
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreateForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }
}