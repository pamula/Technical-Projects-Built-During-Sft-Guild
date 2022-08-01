/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.service;

import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dao.VendingMachineAuditDao;

/**
 *
 * @author Prathima
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws NoSuchItemInInventoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
