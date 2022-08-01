/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Prathima
 */
public class Tax {
   
    private String stateAbbrevation;
    private String stateName;
    private BigDecimal taxRate;

   
    public void setStateAbbrevation(String stateAbbreviation){
        this.stateAbbrevation = stateAbbreviation;
    }
    public String getStateAbbrevation() {
        return stateAbbrevation;
    }

    @Override
    public String toString() {
        return "Tax{" + "stateAbbrevation=" + stateAbbrevation + ", stateName=" + stateName + ", taxRate=" + taxRate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.stateAbbrevation, other.stateAbbrevation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        return true;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
}
