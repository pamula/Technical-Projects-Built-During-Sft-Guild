/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Prathima
 */
public class Organization {
   
    private int organizationId;
    
    @NotBlank(message = "Organization name must not be empty.")
    @Size(max = 25, message = "Organization name must be less than 25 characters.")
     @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="name should have only string values")
    private String orgName;
    
    
    @Size(max = 25, message = "description must be less than 25 characters.")
     @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="description should have only string values")
    private String orgDescription ;
    
    @NotBlank(message = "Address must not be empty.")
    @Size(max = 30, message = "Address name must be less than 30 characters.")
     @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="address should have only string values")
    private String orgAddress;
    
    

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.organizationId;
        hash = 97 * hash + Objects.hashCode(this.orgName);
        hash = 97 * hash + Objects.hashCode(this.orgDescription);
        hash = 97 * hash + Objects.hashCode(this.orgAddress);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.orgAddress, other.orgAddress)) {
            return false;
        }
        return true;
    }
    
   

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }
}
