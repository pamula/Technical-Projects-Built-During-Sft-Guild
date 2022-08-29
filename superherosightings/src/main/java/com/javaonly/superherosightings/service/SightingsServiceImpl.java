/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.service;

import com.javaonly.superherosightings.dao.HeroOrVillianDao;
import com.javaonly.superherosightings.dao.LocationDao;
import com.javaonly.superherosightings.dao.OrganizationDao;
import com.javaonly.superherosightings.dao.SightingsDao;
import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Sightings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */
@Repository
public class SightingsServiceImpl implements SightingsServiceLayer{
    
    @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;

    @Override
    public Sightings addSighting(Sightings sighting) {
        return sightingsDao.addSighting(sighting);
         
    }

    @Override
    public Sightings getSightingById(int id) {
        return sightingsDao.getSightingById(id);
        
    }

    @Override
    public List<Sightings> getAllSightings() {
        List<Sightings> allSightings = sightingsDao.getAllSightings();
        sightingAndLocation(allSightings);
        sightingAndHeros(allSightings);
        return allSightings;
        
    }
   
    private void sightingAndLocation(List<Sightings> allSightings){
     
      List<Location> locations = locationDao.getAllLocations();
      
        for(Sightings sightings:allSightings){
        
       sightings.setLocation(sightingsDao.getOneLocation(sightings.getSightingId()));
        }
        
       
    }
   
    private void sightingAndHeros(List<Sightings> allSightings){
       List<HerosOrVillians> allHeros=null;
        for(Sightings sightings:allSightings){
           //allHeros = sightingsDao.getHeros(sightings.getHeroOrVillianId());
          sightings.setHeroOrVillian(sightingsDao.getOneHero(sightings.getSightingId()));
         
        }
    }

    @Override
    public void updateSighting(Sightings sighting) {
        
        sightingsDao.updateSighting(sighting);
    }

    @Override
    public void deleteSightingById(int id) {
       sightingsDao.deleteSightingById(id);
    }



    @Override
    public List<Sightings> recentSightings() {
        List<Sightings> allSightings = sightingsDao.latestSightings();
        sightingAndLocation(allSightings);
        sightingAndHeros(allSightings);
        return allSightings;
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
