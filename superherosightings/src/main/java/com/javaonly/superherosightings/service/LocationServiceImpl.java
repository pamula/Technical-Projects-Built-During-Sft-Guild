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
import com.javaonly.superherosightings.dto.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */

@Repository
public class LocationServiceImpl implements LocationServiceLayer{
     @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;

    @Override
    public Location getLocationByid(int locationId) {
        Location location = locationDao.getLocationByid(locationId);
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        
        return locationDao.getAllLocations();
         
    }

    @Override
    public Location addLocation(Location location) {
        Location locationNow =  locationDao.addLocation(location);
        return locationNow;
    }
    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locationDao.deleteLocation(locationId);
    }
    
    
}
