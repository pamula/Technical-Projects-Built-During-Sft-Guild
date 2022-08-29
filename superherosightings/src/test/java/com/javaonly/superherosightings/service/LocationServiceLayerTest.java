/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.service;

import com.javaonly.superherosightings.TestApplicationConfiguration;
import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Organization;
import com.javaonly.superherosightings.dto.Sightings;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class LocationServiceLayerTest {
    
     @Autowired
    LocationServiceLayer locationService;
     
     @Autowired
     OrganizationServiceLayer organizationService;
     
     @Autowired
     HeroOrVillianServiceLayer heroOrVillianService;
     
     @Autowired
     SightingsServiceLayer sightingsService;
     
    public LocationServiceLayerTest() {
    }
    
    
    @Before
    public void setUp() {
        
         List<Location> locations = locationService.getAllLocations();
        for(Location allLocations: locations){
            locationService.deleteLocation(allLocations.getLocationId());
        }
        List<Organization> organizations = organizationService.getAllOrganizations();
        for(Organization allOrganizations : organizations){
            organizationService.deleteOrganizationById(allOrganizations.getOrganizationId());
        }
        
        List<HerosOrVillians> herosVillians = heroOrVillianService.getAllHerosOrVillians();
        for(HerosOrVillians allHeros: herosVillians){
           heroOrVillianService.deleteHeroOrVillianById(allHeros.getHeroOrVillianId());
        }
        
        List<Sightings> sightings = sightingsService.getAllSightings();
        for(Sightings allSightings : sightings){
            sightingsService.deleteSightingById(allSightings.getSightingId());
        }
    }
    
    /**
     * Test of getLocationByid method, of class LocationServiceLayer.
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
        
      aLocation = locationService.addLocation(aLocation);
        
        Location locationFromService = locationService.getLocationByid(aLocation.getLocationId());
        assertEquals(aLocation,locationFromService);
        
    }

    /**
     * Test of getAllLocations method, of class LocationServiceLayer.
     */
    @Test
    public void testGetAllLocations() {
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        
         Location fromService = locationService.addLocation(aLocation);
         
       List<Location> locationsFromService = locationService.getAllLocations();
       
        
        assertNotNull(locationsFromService,"Locations must not be null");
    }

    /**
     * Test of addLocation method, of class LocationServiceLayer.
     */
    @Test
    public void testAddLocation() {
         Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromService = locationService.addLocation(aLocation);
        assertEquals(aLocation,fromService);
        
    }

    /**
     * Test of updateLocation method, of class LocationServiceLayer.
     */
    @Test
    public void testUpdateLocation() {
        
         Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        
       aLocation = locationService.addLocation(aLocation);
        Location locationFromService = locationService.getLocationByid(aLocation.getLocationId());
        assertEquals(locationFromService,aLocation);
        
        locationFromService.setLocationName("draper");
        locationService.updateLocation(locationFromService);
        
        assertNotEquals(aLocation,locationFromService);
    }

    /**
     * Test of deleteLocation method, of class LocationServiceLayer.
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
        
        Location fromService = locationService.addLocation(aLocation);
        
        Location locationFromService = locationService.getLocationByid(aLocation.getLocationId());
          
        assertEquals(locationFromService, fromService);
        
        locationService.deleteLocation(aLocation.getLocationId());
        
        fromService = locationService.getLocationByid(aLocation.getLocationId());
        assertNull(fromService);
        
    }

    
    
}
