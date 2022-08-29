/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.controller;


import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.service.HeroOrVillianServiceLayer;
import com.javaonly.superherosightings.service.LocationServiceLayer;
import com.javaonly.superherosightings.service.OrganizationServiceLayer;
import com.javaonly.superherosightings.service.SightingsServiceLayer;
import java.math.BigDecimal;
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
public class LocationController {
    
     @Autowired
    LocationServiceLayer locationService;
    
    @Autowired
    OrganizationServiceLayer organizationService;
    
    @Autowired
    HeroOrVillianServiceLayer heroOrVillianService;
    
    @Autowired
    SightingsServiceLayer sightingsService;
    
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
     Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
     
    //get all locations
    @GetMapping("locations")
    public String getLocations(Model model){
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors",violations);
          return "locations";
    }
    
    //get one location detail 
    @GetMapping("locationDetails")
    public String courseDetail(Integer locationId, Model model) {
        Location location = locationService.getLocationByid(locationId);
        model.addAttribute("location", location);
        return "locationDetails";
    }
    
    
    //to pick details of one location based on id 
    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location oneLocation = locationService.getLocationByid(locationId);
        
        model.addAttribute("location",oneLocation);
        return "editLocation";
    }
    
    //add location
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request){
        String locationName = request.getParameter("locationName");
        String locationDescription = request.getParameter("locationDescription");
        String locationAddress = request.getParameter("locationAddress");
        BigDecimal lat =new BigDecimal( request.getParameter("lat"));
        BigDecimal lng = new BigDecimal(request.getParameter("lng"));
        Location location = new Location();
        location.setLocationName(locationName);
        location.setLocationDescription(locationDescription);
        location.setLocationAddress(locationAddress);
        location.setLat(lat);
        location.setLng(lng);
        
       
            violations = validate.validate(location);

            if(violations.isEmpty()) {
             locationService.addLocation(location);
                  }
        //locationService.addLocation(location);
        return "redirect:/locations";
        
    }
    
    
     //delete location and return to locations html page
    @GetMapping("deleteLocation")
     public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("locationId"));
        locationService.deleteLocation(id);
        
        return "redirect:/locations";
   
}
   @PostMapping("editLocation")
   public String performEditLocation(@Valid Location location ,BindingResult result){
       
       if(result.hasErrors()) {
        return "editLocation";
    }
       locationService.updateLocation(location);
       return "redirect:/locations";
   }
     
     

    
}
