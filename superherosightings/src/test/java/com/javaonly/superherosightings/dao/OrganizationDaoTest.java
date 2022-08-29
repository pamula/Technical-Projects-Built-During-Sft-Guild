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
import java.util.List;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
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

public class OrganizationDaoTest {
    
     @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;
   
    
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
    
     
    public OrganizationDaoTest() {
    }
    
   
    
    /**
     * Test of addOrganization method, of class OrganizationDao.
     */
    @Test
    public void testAddOrganization() {
        
        Organization org = new Organization();
        org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
       Organization org1= organizationDao.addOrganization(org);
        assertEquals(org,org1);
        
    }

    
    
    /**
     * Test of getOrganizationById method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganizationById() {
         Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
       org= organizationDao.addOrganization(org);
       Organization org1 = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(org,org1);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
        
         Organization fromDao = organizationDao.addOrganization(org);
         
       List<Organization> orgsFromDao = organizationDao.getAllOrganizations();
       
        
        assertNotNull(orgsFromDao,"Organizationa must not be null");
        
        
    }

    
    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         
        
        
       org = organizationDao.addOrganization(org);
        Organization organization = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(organization,org);
        
        organization.setOrgName("marvel");
        organizationDao.updateOrganization(organization);
        
        assertNotEquals(org,organization);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDao.
     */
    @Test
    public void testDeleteOrganizationById() {
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
                
        HerosOrVillians heroVillian = new HerosOrVillians();
        heroVillian.setName("spiderman");
        heroVillian.setDescription("good");
        heroVillian.setIsHero(true);
       // heroVillian.setIsHero("true");
        heroVillian.setSuperPower("spiderweb");
        heroVillian.setOrganizationId(org.getOrganizationId());
        
        //heroVillian = heroOrVillianDao.addHeroOrVillian(heroVillian);
        
        Organization org1 = organizationDao.addOrganization(org);

        
        Organization org2 = organizationDao.getOrganizationById(org1.getOrganizationId());
        
        assertEquals(org2,org1);
        organizationDao.deleteOrganizationById(org.getOrganizationId());
        org1 = organizationDao.getOrganizationById(org.getOrganizationId());
        assertNull(org1);
    }

    
    
}
