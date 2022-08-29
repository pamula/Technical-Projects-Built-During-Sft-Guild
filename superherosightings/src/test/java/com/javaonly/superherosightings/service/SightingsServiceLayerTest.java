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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class SightingsServiceLayerTest {
    
    
     
     @Autowired
    LocationServiceLayer locationService;
     
     @Autowired
     OrganizationServiceLayer organizationService;
     
     @Autowired
     HeroOrVillianServiceLayer heroOrVillianService;
     
     @Autowired
     SightingsServiceLayer sightingsService;
    
    public SightingsServiceLayerTest() {
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
     * Test of addSighting method, of class SightingsServiceLayer.
     */
    @Test
    public void testAddSightingAndGetById() throws Exception {
        
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
      
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromService = locationService.addLocation(aLocation);
        
          
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromService.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsService.addSighting(sightingOne);
       Sightings sightingFromService = sightingsService.addSighting(sightingOne);
//        

//        
        assertSame(sightingFromService,sightingOne);
       
    }

    /**
     * Test of getSightingById method, of class SightingsServiceLayer.
     */
    @Test
    public void testGetSightingById() {
    }

    /**
     * Test of getAllSightings method, of class SightingsServiceLayer.
     */
    @Test
    public void testGetAllSightings() throws Exception{
        
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromService = locationService.addLocation(aLocation);
        
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromService.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsService.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsService.getAllSightings();
       assertNotNull(allSightings);
    }

    /**
     * Test of updateSighting method, of class SightingsServiceLayer.
     */
    @Test
    public void testUpdateSighting() throws Exception{
        
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromService = locationService.addLocation(aLocation);
        
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromService.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsService.addSighting(sightingOne);
       Sightings sightingFromService = sightingsService.getSightingById(sightingOne.getSightingId());
       sightingFromService.setDescription("spiderman series are good");
       sightingsService.updateSighting(sightingFromService);
       
       assertNotEquals(sightingOne,sightingFromService);
    }

    /**
     * Test of deleteSightingById method, of class SightingsServiceLayer.
     */
    @Test
    public void testDeleteSightingById() throws Exception {
        
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
        //heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationService.addLocation(aLocation);
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsService.addSighting(sightingOne);
       sightingsService.deleteSightingById(sightingOne.getSightingId());
       Sightings sightingTwo = sightingsService.getSightingById(sightingOne.getSightingId());
       assertNull(sightingTwo);
    }

    /**
     * Test of recentSightings method, of class SightingsServiceLayer.
     */
    @Test
    public void testRecentSightings() throws Exception {
        
          Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        
        //one more hero
        HerosOrVillians heroVilliantwo = new HerosOrVillians();
        heroVilliantwo.setName("superman");
        heroVilliantwo.setDescription("good");
        heroVilliantwo.setIsHero(true);
       
        heroVilliantwo.setSuperPower("speed");
        heroVilliantwo.setOrganizationId(org.getOrganizationId());
       heroVilliantwo = heroOrVillianService.addHeroOrVillian(heroVilliantwo);
        
        
         
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromService = locationService.addLocation(aLocation);
        //ome more location 
        Location LocationTwo = new Location();
        LocationTwo.setLocationName("draper");
        LocationTwo.setLocationAddress("Downtown draper");
        LocationTwo.setLocationDescription("utah");
        LocationTwo.setLat(new BigDecimal("14.24568912"));
        LocationTwo.setLng(new BigDecimal("112.85678912"));
        
      Location   fromServiceTwo = locationService.addLocation(LocationTwo);
        
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromService.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       sightingOne = sightingsService.addSighting(sightingOne);
       //add second sighting 
       Sightings sightingTwo = new Sightings();
        sightingTwo.setDateSighted(LocalDate.now());
        sightingTwo.setDescription("hi");
         sightingTwo.setLocationId(fromServiceTwo.getLocationId());
        sightingTwo.setHeroOrVillianId(heroVilliantwo.getHeroOrVillianId());
       
        
       sightingTwo = sightingsService.addSighting(sightingTwo);
       
       
       List<Sightings> allSightings = sightingsService.recentSightings();
       assertNotNull(allSightings);
    }

    
    
}
