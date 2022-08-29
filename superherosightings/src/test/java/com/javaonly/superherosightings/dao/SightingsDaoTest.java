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

public class SightingsDaoTest {
    
     @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;
    
    public SightingsDaoTest() {
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
     * Test of addSighting method, of class SightingsDao.
     */
    @Test
    public void testAddSightingAndGetById() {
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
          
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       Sightings sightingFromDao = sightingsDao.addSighting(sightingOne);
//        
//        Sightings sightingTwo = sightingsDao.getSightingById(sightingOne.getSightingId());
//        
        assertSame(sightingFromDao,sightingOne);
       
    }

    /**
     * Test of getSightingById method, of class SightingsDao.
     */
    @Test
    public void testGetSightingById() {
    }

    /**
     * Test of getAllSightings method, of class SightingsDao.
     */
    @Test
    public void testGetAllSightings() {
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
        Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsDao.getAllSightings();
       assertNotNull(allSightings);
        
    }

    /**
     * Test of updateSighting method, of class SightingsDao.
     */
    @Test
    public void testUpdateSighting() {
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       Sightings sightingFromDao = sightingsDao.getSightingById(sightingOne.getSightingId());
       sightingFromDao.setDescription("spiderman series are good");
       sightingsDao.updateSighting(sightingFromDao);
       
       assertNotEquals(sightingOne,sightingFromDao);
       
        
        
    }

    /**
     * Test of deleteSightingById method, of class SightingsDao.
     */
    @Test
    public void testDeleteSightingById() {
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
        //heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       sightingsDao.deleteSightingById(sightingOne.getSightingId());
       Sightings sightingTwo = sightingsDao.getSightingById(sightingOne.getSightingId());
       assertNull(sightingTwo);
        
    }

    /**
     * Test of getSuperHeroSightingByLocAndDate method, of class SightingsDao.
     */
    @Test
    public void testGetSuperHeroSightingByLocAndDate() {
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsDao.getAllSightings();
       List<Sightings> allSightingsForLocDate = sightingsDao.getSuperHeroSightingByLocAndDate(fromDao.getLocationName(), sightingOne.getDateSighted().toString());
        assertNotNull(allSightingsForLocDate);
    }

    /**
     * Test of getAllSuperHerosForALocation method, of class SightingsDao.
     */
    @Test
    public void testGetAllSuperHerosForALocation() {
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsDao.getAllSuperHerosForALocation(fromDao.getLocationName());
       assertNotNull(allSightings);
        
    }

    /**
     * Test of getAllLocationsOfASuperHero method, of class SightingsDao.
     */
    @Test
    public void testGetAllLocationsOfASuperHero() {
        
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsDao.getAllLocationsOfASuperHero(heroVillian.getName());
       assertNotNull(allSightings);
    }

    /**
     * Test of getAllSightingsForADate method, of class SightingsDao.
     */
    @Test
    public void testGetAllSightingsForADate() {
         Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
        // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
         Sightings sightingOne = new Sightings();
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
         sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
       
        
       sightingOne = sightingsDao.addSighting(sightingOne);
       List<Sightings> allSightings = sightingsDao.getAllSightingsForADate(sightingOne.getDateSighted().toString());
       assertNotNull(allSightings);
    }

   
   
    
}
