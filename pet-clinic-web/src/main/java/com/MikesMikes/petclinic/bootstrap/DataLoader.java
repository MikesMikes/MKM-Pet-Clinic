package com.MikesMikes.petclinic.bootstrap;

import com.MikesMikes.model.Owner;
import com.MikesMikes.model.Vet;
import com.MikesMikes.services.OwnerService;
import com.MikesMikes.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

//    public DataLoader() {
//        ownerService = new OwnerServiceMap();
//        vetService = new VetServiceMap();
//    }

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Wetstone");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glane");

        ownerService.save(owner2);

        System.out.println("Loaded owners...");
        
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded vets...");

//        System.out.println(vetService.findById(1L).getFirstName());
    }
}
