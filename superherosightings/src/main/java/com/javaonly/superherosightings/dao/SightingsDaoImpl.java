/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dao.HeroOrVillianDaoImpl.HeroOrVillianMapper;
import com.javaonly.superherosightings.dao.LocationDaoImpl.LocationMapper;
import com.javaonly.superherosightings.dto.HerosOrVillians;
import com.javaonly.superherosightings.dto.Location;
import com.javaonly.superherosightings.dto.Sightings;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
public class SightingsDaoImpl implements SightingsDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Sightings getSightingById(int id) {
        try{
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sightingId=?";
            Sightings sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingsMapper(),id);

            return sighting;
            
        }catch(DataAccessException ex){
            return null;
        }
        
    }
    
    
    
     @Override
    public List<Sightings> getAllSightings() {
        DateFormat DateFmt = new SimpleDateFormat("yyyy-mm-dd");
        
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings";
        
        return jdbc.query(SELECT_ALL_SIGHTINGS, new SightingsMapper());
         
    }
    
   
    @Override
    @Transactional
    public Sightings addSighting(Sightings sighting) {

       final String INSERT_SIGHTING = " INSERT INTO sightings(dateSighted,description,locationId,heroOrVillianId) VALUES(?,?,?,?)";

       jdbc.update(INSERT_SIGHTING, sighting.getDateSighted(),
               sighting.getDescription(),
               sighting.getLocationId(),
               sighting.getHeroOrVillianId());
       int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
       sighting.setSightingId(newId);
       return sighting;
        
    }

    @Override
    //@Transactional
    public void updateSighting(Sightings sighting) {

        final String UPDATE_SIGHTING =" UPDATE sightings SET dateSighted = ?, description= ?, "
                +" locationId = ?, heroOrVillianId = ? WHERE sightingId = ? ";
        jdbc.update(UPDATE_SIGHTING,
               sighting.getDateSighted(),
                sighting.getDescription(),
                sighting.getLocationId(),
                sighting.getHeroOrVillianId(),
                sighting.getSightingId());
        
    }
   
    //A user must be able to record a superhero/supervillain sighting for a particular location and date.
    @Override
    public List<Sightings> getSuperHeroSightingByLocAndDate(String location, String date) {
        final String GET_HEROSIGHTING_BY_LOCANDATE = "select * from herosOrVillians  join sightings on sightings.heroOrVillianId=herosOrVillians.heroOrVillianId " 
  + "join location on sightings.locationId=location.locationId where sightings.dateSighted=? and location.locationName=?";
  
        return jdbc.query(GET_HEROSIGHTING_BY_LOCANDATE , new SightingsMapper(),date,location);

                
    }

//The system must be able to report all of the superheroes sighted at a particular location.
    @Override
    public List<Sightings> getAllSuperHerosForALocation(String locationName) {
        
        final String GET_HEROS_FORLOC = "SELECT * FROM herosOrVillians  JOIN sightings ON " +
                                  " sightings.heroOrVillianId=herosOrVillians.heroOrVillianId " +
"JOIN location ON sightings.locationId=location.locationId WHERE location.locationName = ?";
        
        
          return  jdbc.query(GET_HEROS_FORLOC, new SightingsMapper(),locationName);
        
    }
    
    
    @Override
    public List<HerosOrVillians> getHeros(int id){
        final String GET_ALL_HEROS = "SELECT * FROM herosOrVillians  JOIN sightings ON " +
                             " sightings.heroOrVillianId=herosOrVillians.heroOrVillianId where sightings.heroOrVillianId=?";
        return jdbc.query(GET_ALL_HEROS, new HeroOrVillianMapper(),id);
    }
    
    @Override
    public List<Location> getLocations(int id){
        final String GET_ALL_LOCATIONS = "SELECT * FROM location  JOIN sightings ON " +
                             " sightings.locationId=location.locationId where sightings.locationId=?";
        return jdbc.query(GET_ALL_LOCATIONS,new LocationMapper(),id);
    }
    
    @Override
    public Location getOneLocation(int id){
        final String GET_ONE_LOCATION = "SELECT * FROM location  JOIN sightings ON " +
                             " sightings.locationId=location.locationId where sightings.sightingId=?";
        return jdbc.queryForObject(GET_ONE_LOCATION,new LocationMapper(),id);
    }
    
    @Override
    public HerosOrVillians getOneHero(int id){
        final String GET_ONE_HEROS = "SELECT * FROM herosOrVillians  JOIN sightings ON " +
                             " sightings.heroOrVillianId=herosOrVillians.heroOrVillianId where sightingId=?";
        return jdbc.queryForObject(GET_ONE_HEROS, new HeroOrVillianMapper(),id);
    }
   
    
    @Override
    public List<Sightings> getAllLocationsOfASuperHero(String heroName) {
       final String GET_ALL_LOCOFHERO = "select * from location join sightings on sightings.locationId = location.locationId " +
"  join herosOrVillians on sightings.heroOrVillianId = herosOrVillians.heroOrVillianId where herosOrVillians.name= ? ";
       return jdbc.query(GET_ALL_LOCOFHERO, new SightingsMapper(),heroName);
        
    }

    @Override
   public List<Sightings> getAllSightingsForADate(String date) {
       final String GET_ALL_SIGHTINGS_FORADATE = "select * from sightings where sightings.dateSighted=?";
    

       
           return jdbc.query(GET_ALL_SIGHTINGS_FORADATE, new SightingsMapper(),date);
      
    }
     @Override
     @Transactional
    public void deleteSightingById(int id) {
         final String DELETE_SIGHTING = " DELETE FROM sightings WHERE sightingId = ? ";
         jdbc.update(DELETE_SIGHTING,id);
    }

    @Override
    public List<Sightings> latestSightings() {
        final String RECENT_SIGHTINGS = "SELECT * FROM sightings ORDER BY sightingId Desc ";
        return jdbc.query(RECENT_SIGHTINGS, new SightingsMapper());
        //To change body of generated methods, choose Tools | Templates.
    }

   

    
    
    public static final class SightingsMapper implements RowMapper<Sightings>{
        
        @Override
        public Sightings mapRow(ResultSet rs, int index) throws SQLException{
              
            Sightings sighting = new Sightings();
             
          sighting.setSightingId(rs.getInt("sightingId"));
          
          sighting.setDateSighted(rs.getDate("dateSighted").toLocalDate());

            sighting.setDescription(rs.getString("description"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setHeroOrVillianId(rs.getInt("heroOrVillianId"));
            return sighting;
        }
    }
    
}
