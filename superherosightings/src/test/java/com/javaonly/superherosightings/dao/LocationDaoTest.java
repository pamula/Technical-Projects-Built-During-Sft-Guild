/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.TestApplicationConfiguration;
import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Organization;
import com.javaonly.superherosightings.dto.Sightings;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Prathima
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

public class LocationDaoTest {
    
    @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;
    
    
    public LocationDaoTest() {
    }
    
    
    @Before
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location allLocations: locations){
            locationDao.deleteLocation(allLocations.getLocationId());
        }
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization allOrganizations : organizations){
            organizationDao.deleteOrganizationById(allOrganizations.getOrganizationId());
        }
        
        List<HerosOrVillians> herosVillians = heroOrVillianDao.getAllHerosOrVillians();
        for(HerosOrVillians allHeros: herosVillians){
           heroOrVillianDao.deleteHeroOrVillianById(allHeros.getHeroOrVillianId());
        }
        
        List<Sightings> sightings = sightingsDao.getAllSightings();
        for(Sightings allSightings : sightings){
            sightingsDao.deleteSightingById(allSightings.getSightingId());
        }
    }
    
    
    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddLocation() {
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        assertEquals(aLocation,fromDao);
    }
   
    /**
     * Test of getLocationByName method, of class LocationDao.
     */
    @Test
    public void testGetLocationByName() {
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
       
        List<Location> locationFromDao = locationDao.getLocationByName("slc");
        List<Location> allLocations;
        allLocations = locationDao.getAllLocations();
        assertEquals(locationFromDao,allLocations);
    }

    /**
     * Test of getLocationByid method, of class LocationDao.
     */
    @Test
    public void testGetLocationByid() {
        Location aLocation = new Location();
        aLocation.setLocationId(1);
        aLocation.setLocationName("slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
      aLocation = locationDao.addLocation(aLocation);
        
        Location locationFromDao = locationDao.getLocationByid(aLocation.getLocationId());
        assertEquals(aLocation,locationFromDao);
    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        
         Location fromDao = locationDao.addLocation(aLocation);
         
       List<Location> locationsFromDao = locationDao.getAllLocations();
       
        
        assertNotNull(locationsFromDao,"Locations must not be null");
        
        
    }

    

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        
       aLocation = locationDao.addLocation(aLocation);
        Location locationFromDao = locationDao.getLocationByid(aLocation.getLocationId());
        assertEquals(locationFromDao,aLocation);
        
        locationFromDao.setLocationName("draper");
        locationDao.updateLocation(locationFromDao);
        
        assertNotEquals(aLocation,locationFromDao);
        
        
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
        sightingOne.setHeroOrVillianId(0);
        sightingOne.setLocationId(1);
        
        Location fromDao = locationDao.addLocation(aLocation);
        
        Location locationFromDao = locationDao.getLocationByid(aLocation.getLocationId());
          
        assertEquals(locationFromDao, fromDao);
        
        locationDao.deleteLocation(aLocation.getLocationId());
        
        fromDao = locationDao.getLocationByid(aLocation.getLocationId());
        assertNull(fromDao);
        
    }

   
    
}
