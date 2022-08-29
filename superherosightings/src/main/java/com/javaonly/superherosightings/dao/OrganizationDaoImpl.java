/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Prathima
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao{
    
    @Autowired
   JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int organizationId) {
        try{
            final String GET_ORGANIZATION_BYID = "SELECT * FROM organization WHERE organizationId=?";
            return jdbc.queryForObject(GET_ORGANIZATION_BYID, new OrganizationMapper(),organizationId);
            
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
    }
    
    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        
        final String INSERT_ORGANIZATION = "INSERT INTO organization(orgName,orgDescription,orgAddress) VALUES(?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrgName(),organization.getOrgDescription(),
                       organization.getOrgAddress());
         int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION ="UPDATE organization SET orgName=?, orgDescription=?,orgAddress=? WHERE organizationId=?";
         jdbc.update(UPDATE_ORGANIZATION, 
                 organization.getOrgName(),
                 organization.getOrgDescription(),
                 organization.getOrgAddress(),organization.getOrganizationId());
         
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        
        final String DELETE_ORGANIZATION_INHEROSORVILLIANS = "DELETE FROM herosOrVillians WHERE organizationId=?";
        jdbc.update(DELETE_ORGANIZATION_INHEROSORVILLIANS,id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE organizationId=?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }
    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organizationId"));
            organization.setOrgName(rs.getString("orgName"));
            organization.setOrgDescription(rs.getString("orgDescription"));
            organization.setOrgAddress(rs.getString("orgAddress"));
            
            return organization;
        }
    }
    
}
