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
import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Organization;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */
@Repository
public class HeroOrVillianServiceImpl implements HeroOrVillianServiceLayer {
    
     @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;

    @Override
    public HerosOrVillians getHeroOrVillianById(int id) {
        HerosOrVillians oneHero = heroOrVillianDao.getHeroOrVillianById(id);
        
        return oneHero;
    }

    @Override
    public List<HerosOrVillians> getAllHerosOrVillians() {
    
        List<HerosOrVillians> allHeros = heroOrVillianDao.getAllHerosOrVillians();
           orgNameForHero(allHeros);
        return allHeros;
    }
    private void orgNameForHero(List<HerosOrVillians> allHeros){
        
        List<Organization> allOrgs = null;
       for(HerosOrVillians allHerosVillians : allHeros){
       //allOrgs =     heroOrVillianDao.getOrganizationForHero(allHerosVillians.getHeroOrVillianId());
       
       allOrgs =     heroOrVillianDao.getOrganizationForHero(allHerosVillians.getOrganizationId());
       allHerosVillians.setOrgs(heroOrVillianDao.getOneOrgForAHero(allHerosVillians.getHeroOrVillianId()));
       
        } 
       //return allOrgs;
    }
   

    @Override
    public HerosOrVillians addHeroOrVillian(HerosOrVillians heroOrVillian) throws HeroOrVillianDuplicateException{
         if(heroOrVillianDao.getHeroOrVillianById(heroOrVillian.getHeroOrVillianId())!=null ){
             throw new HeroOrVillianDuplicateException("Error could not create heroOrVillian"
                    + heroOrVillian.getName() + "already exists");
         }
         
         try {
             validateItemData(heroOrVillian);
         } catch (HeroOrVillainVallidationException ex) {
             Logger.getLogger(HeroOrVillianServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      return  heroOrVillianDao.addHeroOrVillian(heroOrVillian);
        
    }

    @Override
    public void updateHeroOrVillian(HerosOrVillians heroOrVillian) throws HeroOrVillianDuplicateException{
//        
         try {
             validateItemData(heroOrVillian);
         } catch (HeroOrVillainVallidationException ex) {
             Logger.getLogger(HeroOrVillianServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         heroOrVillianDao.updateHeroOrVillian(heroOrVillian);
    }
    @Override
    public void deleteHeroOrVillianById(int id) {
        heroOrVillianDao.deleteHeroOrVillianById(id);
        //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Organization> getAllOrganizationOfAheroOrVillian(int id) {
        
        return heroOrVillianDao.getOrganizationForHero(id);
        //To change body of generated methods, choose Tools | Templates.
    }
    
     private void validateItemData(HerosOrVillians heroOrVillian) throws HeroOrVillainVallidationException {
         //String isHeroToStr = heroOrVillian.getIsHero().toString();
        if (heroOrVillian.getIsHero() == null  ) {
            throw new HeroOrVillainVallidationException("ERROR,  Hero is either true or false only ");
    }
    
     } 
}
