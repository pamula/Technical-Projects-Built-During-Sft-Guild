/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Prathima
 */
public class TaxAuditDaoImpl implements TaxAuditDao{
    
    public static final String AUDIT_TAXFILE = "auditTax.txt";
   public void writeAuditEntry(String entry) throws DataPersistenceException{
       PrintWriter out;
       try{
           //here we open audi file in append mode i.e write entry to file
           //not overwrite everything which is already left in file
           //these things are done by using true here
           out = new PrintWriter(new FileWriter(AUDIT_TAXFILE, true));
           
       }catch(IOException e){
           throw new DataPersistenceException("could not persist audit info for products", e);
       }
       //here each entry will have local time when its entered to file etc..
       LocalDateTime timestamp = LocalDateTime.now();
       out.println(timestamp.toString() + " : " + entry);
       out.flush();
   }
}
