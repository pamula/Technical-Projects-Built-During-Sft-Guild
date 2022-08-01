/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Tax;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Prathima
 */
public class TaxDaoFileImplTest {
    TaxDao testTaxDao;
    
    public TaxDaoFileImplTest() {
    }
    
   
    
    @BeforeEach
    public void setUp()throws Exception {
        String taxTestFile = "testTax.txt";
        //filewriter to quickly blank the file
        new FileWriter(taxTestFile);
        testTaxDao = new TaxDaoFileImpl(taxTestFile);
    }
   
    @Test
    public void testAddAndGetTaxValueMethod() throws DataPersistenceException {
        //create state test inputs
        
        Tax stateAbbrTax = new Tax();
        String stateAbbreviation = "ut";
        stateAbbrTax.setStateAbbrevation(stateAbbreviation);
        stateAbbrTax.setStateName("utah");
        stateAbbrTax.setTaxRate(new BigDecimal("7.9"));
        
        //add tax values to dao
       // testTaxDao.addTax(stateAbbreviation, stateAbbrTax);
        //get tax values from dao
       // Tax retrieveTaxValues = testTaxDao.getTax(stateAbbreviation);
        //check data is equal
        //assertEquals(stateAbbrTax.getStateAbbrevation(),retrieveTaxValues.getStateAbbrevation(),"sate abbreviation must be same");
        //assertEquals(stateAbbrTax.getStateName(),retrieveTaxValues.getStateName(),"state names must be same");
       
    }
    @Test
    public void testAddAndGetAllTaxValues() throws DataPersistenceException{
        Tax firstTax = new Tax();
        firstTax.setStateAbbrevation("ut");
        firstTax.setStateName("utah");
        firstTax.setTaxRate(new BigDecimal("7.9"));
        
        Tax secondTax = new Tax();
        secondTax.setStateAbbrevation("ca");
        secondTax.setStateName("california");
        secondTax.setTaxRate(new BigDecimal("14.9"));
       // testTaxDao.addTax(firstTax.getStateAbbrevation(), firstTax);
       // testTaxDao.addTax(secondTax.getStateAbbrevation(), secondTax);
        //add to list
//        List<Tax> allTaxes = testTaxDao.getAllTaxes();
//        assertNotNull(allTaxes,"list must not be null");
//        assertEquals(2,allTaxes.size(),"list has 2 taxes");
        
        
        
    }
    
}
