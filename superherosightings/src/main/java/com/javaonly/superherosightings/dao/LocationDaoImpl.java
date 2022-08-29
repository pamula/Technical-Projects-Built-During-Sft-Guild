/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.Location;
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
public class LocationDaoImpl implements LocationDao{
    
    @Autowired
   JdbcTemplate jdbc;
    
 @Override
    public Location getLocationByid(int locationId) {
        try{
            final String GET_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";
          return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), locationId);
        } catch(DataAccessException ex) {
            return null;
        }
         //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Location> getLocationByName(String locationName) {
        
        try {
            final String GET_LOCATION_BY_NAME = "SELECT * FROM location WHERE locationName = ?";
//            return jdbc.query(GET_LOCATION_BY_NAME, new LocationMapper(), locationName);
         return jdbc.query(GET_LOCATION_BY_NAME, new LocationMapper(),locationName);
        
        } catch(DataAccessException ex) {
            return null;
        }
        
         
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_Location = "SELECT * FROM location";
        return jdbc.query(GET_ALL_Location, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(locationName,locationDescription,locationAddress,lat,lng)"+
                                             "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLat(),
                location.getLng());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
        
        
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) {
        
        final String UPDATE_LOCATION= "UPDATE location SET locationName=?,locationDescription= ?,locationAddress=?, " +
                "lat=?, lng=? WHERE locationId= ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLat(),
                location.getLng(),
                location.getLocationId());
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteLocation(int locationId) {
        
         final String DELETE_SIGHTINGS = "DELETE FROM sightings WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTINGS, locationId);
        
        final String DELETE_LOCATION = "DELETE FROM location WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION, locationId);
       //To change body of generated methods, choose Tools | Templates.
    }

   
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setLocationAddress(rs.getString("locationAddress"));
            location.setLat(rs.getBigDecimal("lat"));
            location.setLng(rs.getBigDecimal("lng"));
            
            return location;
        }
    }
    
}
