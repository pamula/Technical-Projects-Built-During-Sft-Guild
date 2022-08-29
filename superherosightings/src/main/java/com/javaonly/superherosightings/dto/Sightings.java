/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Prathima
 */
public class Sightings {
    
    private int sightingId;
     @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateSighted;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 30, message = "description  must be less than 30 characters.")
    private String description;
    
    private int locationId;
    private int heroOrVillianId;
    
    private Location location;
    private HerosOrVillians heroOrVillian;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HerosOrVillians getHeroOrVillian() {
        return heroOrVillian;
    }

    public void setHeroOrVillian(HerosOrVillians heroOrVillian) {
        this.heroOrVillian = heroOrVillian;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.sightingId;
        hash = 47 * hash + Objects.hashCode(this.dateSighted);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + this.locationId;
        hash = 47 * hash + this.heroOrVillianId;
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
        final Sightings other = (Sightings) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.heroOrVillianId != other.heroOrVillianId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.dateSighted, other.dateSighted)) {
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

    public int getHeroOrVillianId() {
        return heroOrVillianId;
    }

    public void setHeroOrVillianId(int heroOrVillianId) {
        this.heroOrVillianId = heroOrVillianId;
    }
//    private Location location;
//    private HerosOrVillians herosOrVillians;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDateSighted() {
       
        return dateSighted;
    }

    public void setDateSighted(LocalDate dateSighted) {
        this.dateSighted = dateSighted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
    
}
