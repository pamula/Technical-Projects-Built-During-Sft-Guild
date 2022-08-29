/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dao.OrganizationDaoImpl.OrganizationMapper;
import com.javaonly.superherosightings.dto.HerosOrVillians;
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
public class HeroOrVillianDaoImpl implements HeroOrVillianDao{
    
    @Autowired
   JdbcTemplate jdbc;
   

    @Override
    public HerosOrVillians getHeroOrVillianById(int id) {
        try {
            final String SELECT_HEROORVILLIAN_BY_ID = "SELECT * FROM herosOrVillians WHERE heroOrVillianId = ?";
          
         return  jdbc.queryForObject(SELECT_HEROORVILLIAN_BY_ID, new HeroOrVillianMapper(), id);
       
        } catch(DataAccessException ex) {
            return null;
        } 
    }
    
    //here u are getting organizations for a hero/Villian
    @Override
    public List<Organization> getOrganizationForHero(int id) {
        final String SELECT_ORGANIZATION_FOR_HEROVILLIAN = "SELECT o.* FROM organization o "
                + "JOIN herosOrVillians h ON h.organizationId = o.organizationId WHERE h.organizationId = ?";
        return jdbc.query(SELECT_ORGANIZATION_FOR_HEROVILLIAN, new OrganizationMapper(), id);
    }

    @Override
    public List<HerosOrVillians> getAllHerosOrVillians() {
        
        final String SELECT_ALL_HEROSORVILLIANS = "SELECT * FROM herosOrVillians";
        
        return jdbc.query(SELECT_ALL_HEROSORVILLIANS, new HeroOrVillianMapper());
    }
    
   
    

    @Override
    @Transactional
    public HerosOrVillians addHeroOrVillian(HerosOrVillians heroOrVillian) {
        final String INSERT_HEROORVILLIAN = "INSERT INTO herosOrVillians(name,description,isHero,superPower,organizationId)VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_HEROORVILLIAN,
                heroOrVillian.getName(),
                heroOrVillian.getDescription(),
                heroOrVillian.getIsHero(),
                heroOrVillian.getSuperPower(),
                heroOrVillian.getOrganizationId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        //heroOrVillian.setOrganizationId(newId);
        heroOrVillian.setHeroOrVillianId(newId);
        return heroOrVillian;
    }

    @Override
    public void updateHeroOrVillian(HerosOrVillians heroOrVillian) {
        final String UPDATE_HEROORVILLIAN = "UPDATE herosOrVillians SET name = ?, description = ?, isHero=?, superPower=?, " +
                "organizationId = ? WHERE heroOrVillianId = ?";
        jdbc.update(UPDATE_HEROORVILLIAN,
                heroOrVillian.getName(),
                heroOrVillian.getDescription(),
                heroOrVillian.getIsHero(),
        heroOrVillian.getSuperPower(),
              heroOrVillian.getOrganizationId(),
              heroOrVillian.getHeroOrVillianId());
         
    }


    //only one org for a hero
    @Override
    public Organization getOneOrgForAHero(int id){
        final String SELECT_ORGS_FOR_HERO = " SELECT * FROM organization JOIN " +
                          " herosOrVillians on herosOrVillians.organizationId = organization.organizationId WHERE herosOrVillians.heroOrVillianId= ?";
   return jdbc.queryForObject(SELECT_ORGS_FOR_HERO,new OrganizationMapper(),id);
    
    }

    @Override
    @Transactional
    public void deleteHeroOrVillianById(int id) {
        
        final String DELETE_HERO_VILLIAN = "DELETE FROM sightings WHERE heroOrVillianId = ?";
        jdbc.update(DELETE_HERO_VILLIAN, id);
        
        final String DELETE_HEROORVILLIAN = "DELETE FROM herosOrVillians WHERE heroOrVillianId = ?";
        jdbc.update(DELETE_HEROORVILLIAN, id);
        
    }

    public static final class HeroOrVillianMapper implements RowMapper<HerosOrVillians> {

        @Override
        public HerosOrVillians mapRow(ResultSet rs, int index) throws SQLException {
            HerosOrVillians heroOrVillian = new HerosOrVillians();
            heroOrVillian.setHeroOrVillianId(rs.getInt("heroOrVillianId"));
            heroOrVillian.setName(rs.getString("name"));
            heroOrVillian.setDescription(rs.getString("description"));
            heroOrVillian.setIsHero(rs.getBoolean("isHero"));
          // heroOrVillian.setIsHero(rs.getString("isHero"));
            heroOrVillian.setSuperPower(rs.getString("superPower"));
            heroOrVillian.setOrganizationId(rs.getInt("organizationId"));
            
            return heroOrVillian;
        }
    }
    
}
