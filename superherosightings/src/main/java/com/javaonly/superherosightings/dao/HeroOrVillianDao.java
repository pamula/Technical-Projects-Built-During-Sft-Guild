/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface HeroOrVillianDao {
    HerosOrVillians getHeroOrVillianById(int id);
    List<HerosOrVillians> getAllHerosOrVillians();
    HerosOrVillians addHeroOrVillian(HerosOrVillians heroOrVillian);
    void updateHeroOrVillian(HerosOrVillians heroOrVillian);
    void deleteHeroOrVillianById(int id);
    
   // List<HerosOrVillians> getAllHerosOrVilliansByOrganizationName(String name);
   // List<Organization> getAllOrganizationOfAheroOrVillian(String name);
    List<Organization> getOrganizationForHero(int id);
    Organization getOneOrgForAHero(int id);
    
}
