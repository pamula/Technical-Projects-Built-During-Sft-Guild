/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dao;

import com.javaonly.superherosightings.dto.Location;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface LocationDao {
    List<Location> getLocationByName(String locationName);
    Location getLocationByid(int locationId);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocation(int locationId);
    
    
}
