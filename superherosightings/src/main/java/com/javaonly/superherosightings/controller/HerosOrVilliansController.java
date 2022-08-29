/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.controller;

import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Organization;
import com.javaonly.superherosightings.service.HeroOrVillainVallidationException;
import com.javaonly.superherosightings.service.HeroOrVillianDuplicateException;
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
//import javax.validation.Valid;
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
public class HerosOrVilliansController {
    
    @Autowired
    LocationServiceLayer locationService;
    
    @Autowired
    OrganizationServiceLayer organizationService;
    
    @Autowired
    HeroOrVillianServiceLayer heroOrVillianService;
    
    @Autowired
    SightingsServiceLayer sightingsService;
    
    Set<ConstraintViolation<HerosOrVillians>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

    
    //get all heros and we need orgs also so pull all orgs so we can use in ui
    
    @GetMapping("allHerosOrVillians")
    public String displayHerosOrVillians(Model model){
        List<HerosOrVillians> herosOrVillians = heroOrVillianService.getAllHerosOrVillians();
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("herosOrVillians",herosOrVillians);
        model.addAttribute("organizations",organizations);
        
        model.addAttribute("errors", violations);
        return "allHerosOrVillians";
        
    }
    
    
    @GetMapping("heroOrVillianDetail")
    public String heroOrVillianDetails(Integer heroOrVillianId, Model model) {
       
          HerosOrVillians herosOrVillians =  heroOrVillianService.getHeroOrVillianById(heroOrVillianId);
       
         Organization organization = organizationService.getOrganizationById(herosOrVillians.getOrganizationId());
       model.addAttribute("herosOrVillians",herosOrVillians);
       model.addAttribute("organization",organization);
       return "heroOrVillianDetail";
   }
    
    @PostMapping("addHeroOrVillian")
    public String addHeroOrVillian(@Valid HerosOrVillians heroOrVillian,BindingResult result,HttpServletRequest request)
                                       throws HeroOrVillianDuplicateException, HeroOrVillainVallidationException {
        
         HerosOrVillians heroVillian = new  HerosOrVillians();

      String heroOrVillianName = request.getParameter("name");
       String description = request.getParameter("description");
      
       String strValForHero = request.getParameter("isHero");
       if(strValForHero.equalsIgnoreCase("true")){
           strValForHero = "true";
       }
       else{
           strValForHero = "false";
       }
      String  superPower = request.getParameter("superPower");
      String  organizationId = request.getParameter("organizationId");
      
        heroVillian.setName(heroOrVillianName);
        heroVillian.setDescription(description);
         heroVillian.setIsHero(Boolean.valueOf(strValForHero));
        heroVillian.setSuperPower(superPower);
       heroVillian.setOrganizationId((Integer.parseInt(organizationId)));
       Organization orgForHero = organizationService.getOrganizationById(heroVillian.getOrganizationId());
       heroVillian.setOrgs(orgForHero);
      
       violations = validate.validate(heroVillian);

                  if(violations.isEmpty()) {
                 heroOrVillianService.addHeroOrVillian(heroVillian);
               }

       return "redirect:/allHerosOrVillians";
        
    }
    
   
    @GetMapping("editHeroOrVillian")
        public String editHeroOrVillian(Integer heroOrVillianId, Model model){ 
        HerosOrVillians herosOrVillians = heroOrVillianService.getHeroOrVillianById(heroOrVillianId);
        Organization organization = organizationService.getOrganizationById(herosOrVillians.getOrganizationId());
        herosOrVillians.setOrgs(organization);
      // List<HerosOrVillians> allHeros = heroOrVillianService.getAllHerosOrVillians();
       List<Organization> organizationss = organizationService.getAllOrganizations();
        model.addAttribute("herosOrVillians", herosOrVillians);
        model.addAttribute("organizations",organizationss);
        model.addAttribute("organization",organization); //this just added now
        return "editHeroOrVillian";
    }
 
      
    @PostMapping("editHeroOrVillian")
     public String editHero( @Valid HerosOrVillians heroOrVillian, BindingResult result,HttpServletRequest request,Model model) throws HeroOrVillianDuplicateException{
     int id = Integer.parseInt(request.getParameter("heroOrVillianId"));
     
      heroOrVillian = heroOrVillianService.getHeroOrVillianById(id);
      heroOrVillian.setName(request.getParameter("name"));
      heroOrVillian.setDescription(request.getParameter("description"));
      heroOrVillian.setIsHero(Boolean.valueOf(request.getParameter("isHero")));
      
      heroOrVillian.setSuperPower(request.getParameter("superPower"));
      heroOrVillian.setOrganizationId(Integer.parseInt(request.getParameter("organizationId")));
      
      heroOrVillianService.updateHeroOrVillian(heroOrVillian);
      return "redirect:/allHerosOrVillians";
     }  
    
     @GetMapping("deleteHeroOrVillian")
public String deleteHero(HttpServletRequest request) {
   
    int id = Integer.parseInt(request.getParameter("heroOrVillianId"));
    heroOrVillianService.deleteHeroOrVillianById(id);
    return "redirect:/allHerosOrVillians";
}


}
