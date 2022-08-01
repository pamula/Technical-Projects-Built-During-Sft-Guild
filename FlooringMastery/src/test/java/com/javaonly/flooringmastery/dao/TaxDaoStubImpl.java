/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class TaxDaoStubImpl implements TaxDao{
    public Tax onlyTax;
    
    public TaxDaoStubImpl(){
        onlyTax = new Tax();
        onlyTax.setStateAbbrevation("ut");
        onlyTax.setStateName("utah");
        onlyTax.setTaxRate(new BigDecimal("7.9"));
    }
    
    public TaxDaoStubImpl(Tax testTax){
        this.onlyTax = testTax;
    }

//    @Override
//    public Tax addTax(String stateAbbrevation, Tax taxInfo) {
//        if(stateAbbrevation.equalsIgnoreCase(onlyTax.getStateAbbrevation())){
//            return onlyTax;
//        }else{
//            return null;
//        }
//    }

    @Override
    public List<Tax> getAllTaxes() throws DataPersistenceException {
        List<Tax> taxList = new ArrayList<>();
        taxList.add(onlyTax);
        return taxList;
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax getTax(String stateAbbreviation) throws DataPersistenceException {
        if(stateAbbreviation.equalsIgnoreCase(onlyTax.getStateAbbrevation())){
            return onlyTax;
        }else{
            return null;
        }
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
