/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.controller;


import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Organization;
import com.javaonly.superherosightings.service.HeroOrVillianServiceLayer;
import com.javaonly.superherosightings.service.LocationServiceLayer;
import com.javaonly.superherosightings.service.OrganizationServiceLayer;
import com.javaonly.superherosightings.service.SightingsServiceLayer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author Prathima
 */

@Controller
public class OrganizationController {
    
   @Autowired
    LocationServiceLayer locationService;
    
    @Autowired
    OrganizationServiceLayer organizationService;
    
    @Autowired
    HeroOrVillianServiceLayer heroOrVillianService;
    
    @Autowired
    SightingsServiceLayer sightingsService;
    

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();
     Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    
    @GetMapping("organizations")
    public String getOrganizations(Model model){
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations",organizations);
        model.addAttribute("errors",violations);
        return "organizations";
    }
    
    
     @GetMapping("organizationDetails")
    public String organizationDetail(Integer organizationId, Model model) {
       
          Organization organization =  organizationService.getOrganizationById(organizationId);
       
       model.addAttribute("organization",organization);
      return "organizationDetails";
   }
    
    //create get mapping to display edit organization page
    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request,Model model){
      
        int id = Integer.parseInt(request.getParameter("organizationId"));
        Organization organization = organizationService.getOrganizationById(id);
        model.addAttribute("organization",organization);
        return "editOrganization";
    
}
    
   
    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request){
        String organizationName = request.getParameter("orgName");
        String organizationDescription = request.getParameter("orgDescription");
        String organizationAddress = request.getParameter("orgAddress");
        Organization organization = new Organization();
        organization.setOrgName(organizationName);
        organization.setOrgDescription(organizationDescription);
        organization.setOrgAddress(organizationAddress);
        
         violations = validate.validate(organization);

            if(violations.isEmpty()) {
             organizationService.addOrganization(organization);
                  }
        
        return "redirect:/organizations";
        
    }
    
    @PostMapping("editOrganization")
    public String performEditOrganization( @Valid Organization organization, BindingResult result){
        if(result.hasErrors()) {
        return "editOrganization";
    }
        organizationService.updateOrganization(organization);
      return "redirect:/organizations";
        
    }
 
     
    @GetMapping("deleteOrganization")
public String deleteOrganization(HttpServletRequest request){
    int id = Integer.parseInt(request.getParameter("organizationId"));
   organizationService.deleteOrganizationById(id);
        return "redirect:/organizations";
}

    
}
