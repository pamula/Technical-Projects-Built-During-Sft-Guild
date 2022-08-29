/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Prathima
 */
public class HerosOrVillians {
    
    private int heroOrVillianId;
    
    @NotBlank(message = "Hero/Villain name must not be empty.")
    @Size(max = 25, message = "Hero/Villain name must be less than 25 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="name should have only string values")
    private String name;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 30, message = "Description must be less than 30 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="name should have only string values")
    private String description;
    
   
    @NotNull
   private Boolean isHero;

    
    @NotBlank(message = "SuperPower must not be empty.")
    @Size(max = 25, message = "SuperPower must be less than 25 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="superpower should have only string values")
    private String superPower;
    
    private int organizationId;
    
    private Organization orgs;

    public Organization getOrgs() {
        return orgs;
    }

    public void setOrgs(Organization orgs) {
        this.orgs = orgs;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.heroOrVillianId;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.isHero);
        hash = 17 * hash + Objects.hashCode(this.superPower);
        hash = 17 * hash + this.organizationId;
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
        final HerosOrVillians other = (HerosOrVillians) obj;
        if (this.heroOrVillianId != other.heroOrVillianId) {
            return false;
        }
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.isHero, other.isHero)) {
            return false;
        }
        return true;
    }

    
   

    public int getHeroOrVillianId() {
        return heroOrVillianId;
    }

    public void setHeroOrVillianId(int heroOrVillianId) {
        this.heroOrVillianId = heroOrVillianId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsHero() {
        return isHero;
       // return Boolean.valueOf(isHeroOrNot);
    }
    
    public void setIsHero(Boolean isHero) {
        this.isHero = isHero;
    }
    

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

   
    
}
