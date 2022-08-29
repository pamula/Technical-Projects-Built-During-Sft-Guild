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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
public class HeroOrVillianDaoTest {
    
    @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;
    
    public HeroOrVillianDaoTest() {
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
     * Test of getHeroOrVillianById method, of class HeroOrVillianDao.
     */
    @Test
    public void testGetHeroOrVillianById() {
    }

    /**
     * Test of getAllHerosOrVillians method, of class HeroOrVillianDao.
     */
    @Test
    public void testGetAllHerosOrVillians() {
        Organization org = new Organization();
         org.setOrganizationId(1);
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
        
        HerosOrVillians heroVillian1 = new HerosOrVillians();
        heroVillian1.setName("superman");
        heroVillian1.setDescription("good");
        heroVillian1.setIsHero(true);
        //heroVillian1.setIsHero("true");
        heroVillian1.setSuperPower("spiderweb");
        heroVillian1.setOrganizationId(org.getOrganizationId());
        
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        List<HerosOrVillians> allHeros;
        allHeros = heroOrVillianDao.getAllHerosOrVillians();
        assertNotNull(allHeros);
    }

    /**
     * Test of addHeroOrVillian method, of class HeroOrVillianDao.
     */
    @Test
    public void testAddHeroOrVillianAndGetById() throws ParseException {
        
        Organization org = new Organization();
         //org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
        org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
        // heroVillian.setHeroOrVillianId(1);
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
       // sightingOne.setSightingId(1);
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
        sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(heroVillian.getHeroOrVillianId());
        sightingOne = sightingsDao.addSighting(sightingOne);

        
        HerosOrVillians hero2 = heroOrVillianDao.getHeroOrVillianById(heroVillian.getHeroOrVillianId());
        assertEquals(hero2,heroVillian);
        
        
    }

    /**
     * Test of updateHeroOrVillian method, of class HeroOrVillianDao.
     */
    @Test
    public void testUpdateHeroOrVillian() throws ParseException {
        Organization org = new Organization();
         
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
         HerosOrVillians heroVillian = new HerosOrVillians();
         
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
       // heroVillian.setIsHero("true");
        heroVillian.setIsHero(true);
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
        
        HerosOrVillians hero = heroOrVillianDao.getHeroOrVillianById(heroVillian.getHeroOrVillianId());
       
        hero.setName("superman");
        heroOrVillianDao.updateHeroOrVillian(hero);
        assertNotEquals(heroVillian,hero);
        
    }

    /**
     * Test of deleteHeroOrVillianById method, of class HeroOrVillianDao.
     */
    @Test
    public void testDeleteHeroOrVillianById() throws ParseException {
        
        Organization org = new Organization();
         //org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
  org = organizationDao.addOrganization(org);
        
        HerosOrVillians heroVillian = new HerosOrVillians();
        //heroVillian.setHeroOrVillianId(1);
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
          heroVillian.setIsHero(true);
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        HerosOrVillians hero1 = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
         Location aLocation = new Location();
        aLocation.setLocationName("slc");
        aLocation.setLocationAddress("Downtown slc");
        aLocation.setLocationDescription("utah");
        aLocation.setLat(new BigDecimal("13.24568912"));
        aLocation.setLng(new BigDecimal("113.85678912"));
        
        Location fromDao = locationDao.addLocation(aLocation);
        
        
         Sightings sightingOne = new Sightings();
         //sightingOne.setSightingId(1);
        sightingOne.setDateSighted(LocalDate.now());
        sightingOne.setDescription("hi");
        sightingOne.setLocationId(fromDao.getLocationId());
        sightingOne.setHeroOrVillianId(hero1.getHeroOrVillianId());
        Sightings sightingOneFromDao = sightingsDao.addSighting(sightingOne);
        
       
        
        heroOrVillianDao.deleteHeroOrVillianById(hero1.getHeroOrVillianId());
        
        HerosOrVillians afterDeleteCheck = heroOrVillianDao.getHeroOrVillianById(hero1.getHeroOrVillianId());
        
        assertNull(afterDeleteCheck);
    }

    /**
     * Test of getAllHerosOrVilliansByOrganizationName method, of class HeroOrVillianDao.
     */
    @Test
    public void testGetAllHerosOrVilliansByOrganizationName() {
         Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
        
        
        HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setHeroOrVillianId(1);
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
          heroVillian.setIsHero(true);
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        //List<HerosOrVillians> allHeros = heroOrVillianDao.getAllHerosOrVilliansByOrganizationName(org.getOrgName());
        
        List<HerosOrVillians> orgNamesForHero = heroOrVillianDao.getAllHerosOrVillians();
        //assertEquals(allHeros,orgNamesForHero);
        
    }

    /**
     * Test of getAllOrganizationOfAheroOrVillian method, of class HeroOrVillianDao.
     */
    @Test
    public void testGetAllOrganizationOfAheroOrVillian() {
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         org = organizationDao.addOrganization(org);
        
        
        
        HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setHeroOrVillianId(1);
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
         heroVillian.setIsHero(true);
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        
        heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
       // List<Organization> allHeros = heroOrVillianDao.getAllOrganizationOfAheroOrVillian(heroVillian.getName());
        List<Organization> allExistingOrgs = organizationDao.getAllOrganizations();
        
       // assertEquals(allHeros,allExistingOrgs);
        
    }

    
    
    
}
