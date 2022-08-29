/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface OrganizationDao {
    Organization getOrganizationById(int organizationId);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
}
