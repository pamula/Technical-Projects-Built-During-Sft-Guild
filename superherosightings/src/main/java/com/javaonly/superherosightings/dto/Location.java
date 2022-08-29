/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Prathima
 */
public class Location {
    private int locationId;
    
    @NotBlank(message = "Location name must not be empty.")
    @Size(max = 30, message = "Location name must be less than 30 characters.")
     @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="name should have only string values")
    private String locationName;
    
    @NotBlank(message = "LocationAddress must not be empty.")
    @Size(max = 30, message = "LocationAddress must be less than 30 characters.")
     @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="location address should have only string values")
    private String locationAddress;
    
    @Size(max = 25, message = "description must be less than 25 characters.")
    private String locationDescription;
    
   @NotNull(message= "latitude must not be empty")
   @Digits(integer=2, fraction=8, message = "Latitude value out of bound should be of decimal(2,8) ")
    private BigDecimal lat;
   
   @NotNull(message= "longitude must not be empty")
   @Digits(integer=3, fraction=8, message = "Longitude value out of bound should be of decimal(3,8)")
    private BigDecimal lng;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.locationId;
        hash = 79 * hash + Objects.hashCode(this.locationName);
        hash = 79 * hash + Objects.hashCode(this.locationAddress);
        hash = 79 * hash + Objects.hashCode(this.locationDescription);
        hash = 79 * hash + Objects.hashCode(this.lat);
        hash = 79 * hash + Objects.hashCode(this.lng);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.lat, other.lat)) {
            return false;
        }
        if (!Objects.equals(this.lng, other.lng)) {
            return false;
        }
        return true;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }
    
    
    
    
}
