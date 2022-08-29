/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Sightings;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface SightingsDao {
    /*
    A user must be able to record a superhero/supervillain sighting for 
    a particular location and date.
The system must be able to report all of the superheroes sighted at a particular location.
The system must be able to report all of the locations where a 
    particular superhero has been seen.
The system must be able to report all sightings (hero and location) for a particular date.
    */
    Sightings addSighting(Sightings sighting);
    Sightings getSightingById(int id);
    List<Sightings> getAllSightings();
     void updateSighting(Sightings sighting);
    void deleteSightingById(int id);
    
    List<Sightings> getSuperHeroSightingByLocAndDate(String locationName,String date);
     List<Sightings> getAllSuperHerosForALocation(String locationName);
     List<Sightings> getAllLocationsOfASuperHero(String heroName);
      List<Sightings> getAllSightingsForADate(String date);
      
    List<HerosOrVillians> getHeros(int id);
   List<Location> getLocations(int id);
    Location getOneLocation(int id);
    HerosOrVillians getOneHero(int id);
    List<Sightings> latestSightings();
    
}
