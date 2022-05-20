package com.MikesMikes.petclinic.controllers;

import com.MikesMikes.petclinic.model.Owner;
import com.MikesMikes.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
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
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
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


    @GetMapping("/new")
    public String createOwnerForm(Model model) {
        log.info("createOwnerForm - ");
        model.addAttribute("owner", Owner.builder().build());

        log.info("createOwnerForm - end");
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result) {
        log.info("processCreateForm - ");

        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner ownerSaved = ownerService.save(owner);

            log.info("processCreateForm - end");
            return "redirect:/owners/" + ownerSaved.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String initUpdateForm(@PathVariable("id") Long id, Model model) {
        log.info("initUpdateForm -");

        model.addAttribute("owner", ownerService.findById(id));

        log.info("initUpdateForm - end");
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdateForm(@Valid Owner owner, BindingResult result,  @PathVariable("id") Long id) {
        log.info("processUpdateForm - ");

        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(id);
            Owner savedOwner = ownerService.save(owner);
            log.info("processUpdateForm - end");
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @RequestMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        log.info("showOwner - ");
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerService.findById(ownerId));
        return mav;
    }
}
