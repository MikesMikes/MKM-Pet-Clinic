package com.MikesMikes.petclinic.bootstrap;

import com.MikesMikes.petclinic.model.*;
import com.MikesMikes.petclinic.services.OwnerService;
import com.MikesMikes.petclinic.services.PetTypeService;
import com.MikesMikes.petclinic.services.SpecialtiesService;
import com.MikesMikes.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtiesService specialtiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtiesService specialtiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtiesService = specialtiesService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (petTypeService.findAll().size() == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("dentistry");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Wetstone");
        owner1.setAddress("123 Brickrel");
        owner1.setCity("Miami");
        owner1.setTelephone("12312444");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setPetType(savedDogType);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glane");
        owner2.setAddress("123 New Brickrel");
        owner1.setCity("Miami");
        owner1.setTelephone("41212444");

        Pet fionasPet = new Pet();
        fionasPet.setName("Fifi");
        fionasPet.setPetType(savedCatType);
        fionasPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(surgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
