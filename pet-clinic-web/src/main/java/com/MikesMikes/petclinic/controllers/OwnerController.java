package com.MikesMikes.petclinic.controllers;

import com.MikesMikes.petclinic.model.Owner;
import com.MikesMikes.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }





    @RequestMapping("/find")
    public String findOwners(Model model) {

        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){

        if (owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()){
            //no owners found
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @RequestMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        log.info("showOwner - ");
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerService.findById(ownerId));
        return mav;
        //todo shits working
    }
}
