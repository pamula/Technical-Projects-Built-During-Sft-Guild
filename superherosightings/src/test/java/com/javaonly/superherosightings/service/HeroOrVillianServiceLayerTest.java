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
public class HeroOrVillianServiceLayerTest {
    
    
     @Autowired
    LocationServiceLayer locationService;
     
     @Autowired
     OrganizationServiceLayer organizationService;
     
     @Autowired
     HeroOrVillianServiceLayer heroOrVillianService;
     
     @Autowired
     SightingsServiceLayer sightingsService;
    
    public HeroOrVillianServiceLayerTest() {
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
     * Test of getHeroOrVillianById method, of class HeroOrVillianServiceLayer.
     */
    @Test
    public void testGetHeroOrVillianById() {
    }

    /**
     * Test of getAllHerosOrVillians method, of class HeroOrVillianServiceLayer.
     */
    @Test
    public void testGetAllHerosOrVillians() throws HeroOrVillianDuplicateException, 
                                            HeroOrVillainVallidationException { 
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
        org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        
        HerosOrVillians heroVillian1 = new HerosOrVillians();
        heroVillian1.setName("superman");
        heroVillian1.setDescription("good");
        heroVillian1.setIsHero(true);
        //heroVillian1.setIsHero("true");
        heroVillian1.setSuperPower("spiderweb");
        heroVillian1.setOrganizationId(org.getOrganizationId());
        
        heroVillian = heroOrVillianService.addHeroOrVillian(heroVillian);
        List<HerosOrVillians> allHeros;
        allHeros = heroOrVillianService.getAllHerosOrVillians();
        assertNotNull(allHeros);
    }

    /**
     * Test of addHeroOrVillian method, of class HeroOrVillianServiceLayer.
     */
    @Test
    public void testAddHeroOrVillianGetById() throws Exception {
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
        
        Location fromDao = locationService.addLocation(aLocation);
        
        Sightings sightingOne = new Sightings();
      
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
        sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
        sightingOne = sightingsService.addSighting(sightingOne);

        
        HerosOrVillians hero2 = heroOrVillianService.getHeroOrVillianById(heroVillian.getHeroOrVillianId());
        assertEquals(hero2,heroVillian);
        
    }

    /**
     * Test of updateHeroOrVillian method, of class HeroOrVillianServiceLayer.
     */
    @Test
    public void testUpdateHeroOrVillian() throws Exception {
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationService.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
         
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
       // heroVillian.setIsHero("true");
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
        
        Location fromDao = locationService.addLocation(aLocation);
        
          
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
        sightingOne = sightingsService.addSighting(sightingOne);
        
        HerosOrVillians hero = heroOrVillianService.getHeroOrVillianById(heroVillian.getHeroOrVillianId());
       
        hero.setName("superman");
        heroOrVillianService.updateHeroOrVillian(hero);
        assertNotEquals(heroVillian,hero);
    }

    /**
     * Test of deleteHeroOrVillianById method, of class HeroOrVillianServiceLayer.
     */
    @Test
    public void testDeleteHeroOrVillianById() throws HeroOrVillianDuplicateException, HeroOrVillainVallidationException {
        
        
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
  org = organizationService.addOrganization(org);
        
        HerosOrVillians heroVillian = new HerosOrVillians();
        //heroVillian.setHeroOrVillianId(1);
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
          heroVillian.setIsHero(true);
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());

        HerosOrVillians hero1 = heroOrVillianService.addHeroOrVillian(heroVillian);
        
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
        sightingOne.setHeroOrVillianId(hero1.getHeroOrVillianId());
        Sightings sightingOneFromDao = sightingsService.addSighting(sightingOne);
        
       
        
        heroOrVillianService.deleteHeroOrVillianById(hero1.getHeroOrVillianId());
        
        HerosOrVillians afterDeleteCheck = heroOrVillianService.getHeroOrVillianById(hero1.getHeroOrVillianId());
        
        assertNull(afterDeleteCheck);
    }

   

   
}
