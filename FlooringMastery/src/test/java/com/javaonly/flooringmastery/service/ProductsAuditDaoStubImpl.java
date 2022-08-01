/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
//import com.javaonly.flooringmastery.dao.NoSuchProductException;
import com.javaonly.flooringmastery.dao.ProductsAuditDao;

/**
 *
 * @author Prathima
 */
public class ProductsAuditDaoStubImpl implements ProductsAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws DataPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
