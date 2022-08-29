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
import com.javaonly.superherosightings.dto.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */
@Repository
public class OrganizationServiceImpl implements OrganizationServiceLayer{
    
    @Autowired
     LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    HeroOrVillianDao heroOrVillianDao;
    
    @Autowired
    SightingsDao sightingsDao;

    @Override
    public Organization getOrganizationById(int id) {
        return organizationDao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }
    
    @Override
    public Organization addOrganization(Organization organization) {
        Organization organizationNow = organizationDao.addOrganization(organization);
        return organizationNow;
    }
    
    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }
    @Override
    public void deleteOrganizationById(int id) {
        organizationDao.deleteOrganizationById(id);
    }
}
