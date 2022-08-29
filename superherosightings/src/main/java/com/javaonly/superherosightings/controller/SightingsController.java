/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.controller;


import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;

import com.javaonly.superherosightings.dto.Sightings;
import com.javaonly.superherosightings.service.HeroOrVillianServiceLayer;
import com.javaonly.superherosightings.service.LocationServiceLayer;
import com.javaonly.superherosightings.service.OrganizationServiceLayer;
import com.javaonly.superherosightings.service.SightingsServiceLayer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author Prathima
 */
@Controller
public class SightingsController {
    
    
     @Autowired
    LocationServiceLayer locationService;
    
    @Autowired
    OrganizationServiceLayer organizationService;
    
    @Autowired
    HeroOrVillianServiceLayer heroOrVillianService;
    
    @Autowired
    SightingsServiceLayer sightingsService;
    
    
    
    @GetMapping("sightings")
    public String getSightings(Model model){
        List<Sightings> sightings = sightingsService.getAllSightings();
        List<Location> locations = locationService.getAllLocations();
        List<HerosOrVillians> herosOrVillians = heroOrVillianService.getAllHerosOrVillians();
       
        model.addAttribute("sightings",sightings);
        model.addAttribute("herosOrVillians",herosOrVillians);
        model.addAttribute("locations",locations);
        
        return "sightings";
    }
    
    @GetMapping(value ={"","/","/index"})
    public String getRecentSightings(Model model){
        List<Sightings> sightings = sightingsService.recentSightings();
        List<Location> locations = locationService.getAllLocations();
        List<HerosOrVillians> herosOrVillians = heroOrVillianService.getAllHerosOrVillians();
       
        model.addAttribute("sightings",sightings);
        model.addAttribute("herosOrVillians",herosOrVillians);
        model.addAttribute("locations",locations);
        
        return "index";
  
   }

    
    @GetMapping("sightingDetails")
    public String sightingDetail(Integer sightingId, Model model) {
       
          Sightings sighting =  sightingsService.getSightingById(sightingId);
       Location location = locationService.getLocationByid(sighting.getLocationId());
       HerosOrVillians heroOrVillian = heroOrVillianService.getHeroOrVillianById(sighting.getHeroOrVillianId());
       model.addAttribute("sightings",sighting);
       model.addAttribute("herosOrVillians",heroOrVillian);
       model.addAttribute("location",location);
       return "sightingDetails";
      
   }
    
    
    @PostMapping("addSighting")
    public String addOneSighting(Sightings sighting, HttpServletRequest request){
      
        
        String date = request.getParameter("userDate");
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate afterFormatted =LocalDate.parse( date,formatter);
        
        String description = request.getParameter("description");
        String locationId = request.getParameter("locationId");
        String heroOrVillianId = request.getParameter("heroOrVillianId");
     
        Sightings oneSighting = new Sightings();
       
        oneSighting.setDateSighted(afterFormatted);
        oneSighting.setDescription(description);
        oneSighting.setLocationId(Integer.parseInt(locationId));
        Location locForSighting = locationService.getLocationByid(oneSighting.getLocationId());
        oneSighting.setLocation(locForSighting);
        oneSighting.setHeroOrVillianId(Integer.parseInt(heroOrVillianId));
        HerosOrVillians heroForSighting = heroOrVillianService.getHeroOrVillianById(oneSighting.getHeroOrVillianId());
        oneSighting.setHeroOrVillian(heroForSighting);
        
        sightingsService.addSighting(oneSighting);
        
        return "redirect:/sightings";
        
    }
    
    
    @GetMapping("editSighting")
        public String editOneSighting(Integer sightingId, Model model){ 
            Sightings sighting = sightingsService.getSightingById(sightingId);
            List<Location> locations = locationService.getAllLocations();
            List<HerosOrVillians> herosOrVillians = heroOrVillianService.getAllHerosOrVillians();
           
            //Location location = sightingsService.getLocationForSighting(sighting.getLocationId());
            Location location = locationService.getLocationByid(sighting.getLocationId());
            HerosOrVillians heroOrVillian = heroOrVillianService.getHeroOrVillianById(sighting.getHeroOrVillianId());
           //HerosOrVillians heroOrVillian = sightingsService.getHeroOrVillianForSighting(sighting.getHeroOrVillianId());
            sighting.setLocation(location);
           sighting.setHeroOrVillian(heroOrVillian);
            
            model.addAttribute("sightings",sighting);
            
            model.addAttribute("locations",locations);
            model.addAttribute("herosOrVillians",herosOrVillians);
          
            return "editSighting";
            
        }
        
        
       @PostMapping("editSighting")
     public String editSighting(HttpServletRequest request){
      int id = Integer.parseInt(request.getParameter("sightingId"));
      Sightings sighting = sightingsService.getSightingById(id);
      String date = request.getParameter("dateSighted");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate afterFormatted =LocalDate.parse( date,formatter);
        
      sighting.setDateSighted(afterFormatted);
      sighting.setDescription(request.getParameter("description"));
      sighting.setLocationId(Integer.parseInt(request.getParameter("locationId")));
      sighting.setHeroOrVillianId(Integer.parseInt(request.getParameter("heroOrVillianId")));
      sightingsService.updateSighting(sighting);
         
      return "redirect:/sightings";
   } 
        
        
        
    @GetMapping("deleteSighting")
public String deleteSighting(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("sightingId"));
    sightingsService.deleteSightingById(id);
    return "redirect:/sightings";
    
}
        
}  
    
   

    

