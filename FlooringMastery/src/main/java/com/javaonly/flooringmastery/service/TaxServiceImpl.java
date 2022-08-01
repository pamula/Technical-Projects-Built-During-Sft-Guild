/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
import com.javaonly.flooringmastery.dao.NoSuchStateException;
import com.javaonly.flooringmastery.dao.TaxAuditDao;
import com.javaonly.flooringmastery.dao.TaxDao;
import com.javaonly.flooringmastery.dto.Tax;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class TaxServiceImpl implements TaxService{
    private TaxDao dao;
    private TaxAuditDao auditTaxDao;
    
    
    public TaxServiceImpl(TaxDao dao,TaxAuditDao auditTaxDao ){
        this.dao = dao;
        this.auditTaxDao = auditTaxDao;
    }
    
//   

    @Override
    public List<Tax> getAllTax()throws DataPersistenceException{
        return dao.getAllTaxes();
    }
    
     @Override
    public Tax getTax(String stateAbbreviation) throws DataPersistenceException{
        return dao.getTax(stateAbbreviation);
    }
    
    private void validateTaxData(Tax tax) throws TaxDataValidationException{
        if(tax!=null || tax.getStateAbbrevation() == null || tax.getStateAbbrevation().trim().length() ==0 
                || tax.getStateName() == null || tax.getStateName().trim().length() == 0
                || tax.getTaxRate() == null ){
            throw new TaxDataValidationException("ERROR ALL FIELDS[STATEABBREVIATION, STATENAME,TAXRATE] REQUIRED");
        }
    }

}
