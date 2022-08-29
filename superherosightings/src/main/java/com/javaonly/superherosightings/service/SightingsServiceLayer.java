/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.service;

import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Sightings;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface SightingsServiceLayer {
    Sightings addSighting(Sightings sighting);
    Sightings getSightingById(int id);
    List<Sightings> getAllSightings();
     void updateSighting(Sightings sighting);
    void deleteSightingById(int id);
    
    List<Sightings> recentSightings();
    
    //Location getLocationForSighting(int id);
   // HerosOrVillians getHeroOrVillianForSighting(int id);
//    
//    void sightingAndLocation(List<Sightings> allSightings);
//    void sightingAndHeros(List<Sightings> allSightings);
}
