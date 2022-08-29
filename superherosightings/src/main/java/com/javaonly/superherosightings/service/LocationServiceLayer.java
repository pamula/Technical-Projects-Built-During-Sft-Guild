/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.service;

import com.javaonly.superherosightings.dto.Location;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface LocationServiceLayer {
    
     Location getLocationByid(int locationId);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocation(int locationId);
    
}
