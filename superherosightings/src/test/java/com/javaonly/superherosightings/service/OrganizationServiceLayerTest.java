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
public class OrganizationServiceLayerTest {
    
    
    @Autowired
    LocationServiceLayer locationService;
     
     @Autowired
     OrganizationServiceLayer organizationService;
     
     @Autowired
     HeroOrVillianServiceLayer heroOrVillianService;
     
     @Autowired
     SightingsServiceLayer sightingsService;
    
    public OrganizationServiceLayerTest() {
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
     * Test of getOrganizationById method, of class OrganizationServiceLayer.
     */
    @Test
    public void testGetOrganizationById() {
        
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
       org= organizationService.addOrganization(org);
       Organization org1 = organizationService.getOrganizationById(org.getOrganizationId());
        assertEquals(org,org1);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationServiceLayer.
     */
    @Test
    public void testGetAllOrganizations() {
         Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
        
         Organization fromService = organizationService.addOrganization(org);
         
       List<Organization> orgsFromService = organizationService.getAllOrganizations();
       
        
        assertNotNull(orgsFromService,"Organizationa must not be null");
    }

    /**
     * Test of addOrganization method, of class OrganizationServiceLayer.
     */
    @Test
    public void testAddOrganization() {
        Organization org = new Organization();
        org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
        
       Organization org1= organizationService.addOrganization(org);
        assertEquals(org,org1);
    }

    /**
     * Test of updateOrganization method, of class OrganizationServiceLayer.
     */
    @Test
    public void testUpdateOrganization() {
        
        Organization org = new Organization();
         org.setOrganizationId(1);
        org.setOrgName("Avengers");
        org.setOrgDescription("good organization");
        org.setOrgAddress("hollywood");
         
        
        
       org = organizationService.addOrganization(org);
        Organization organization = organizationService.getOrganizationById(org.getOrganizationId());
        assertEquals(organization,org);
        
        organization.setOrgName("marvel");
        organizationService.updateOrganization(organization);
        
        assertNotEquals(org,organization);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationServiceLayer.
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
        
        Organization org1 = organizationService.addOrganization(org);

        
        Organization org2 = organizationService.getOrganizationById(org1.getOrganizationId());
        
        assertEquals(org2,org1);
        organizationService.deleteOrganizationById(org.getOrganizationId());
        org1 = organizationService.getOrganizationById(org.getOrganizationId());
        assertNull(org1);
    }

   
    
}
