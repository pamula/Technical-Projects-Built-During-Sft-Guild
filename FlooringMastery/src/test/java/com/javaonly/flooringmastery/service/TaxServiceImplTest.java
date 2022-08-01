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
import com.javaonly.flooringmastery.dao.TaxDaoStubImpl;
import com.javaonly.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Prathima
 */
public class TaxServiceImplTest {
    private TaxService taxService;
    
    public TaxServiceImplTest() {
//        TaxDao taxDao = new TaxDaoStubImpl();
//        TaxAuditDao auditTaxDao = new TaxAuditDaoStubImpl();
//        taxService = new TaxServiceImpl(taxDao, auditTaxDao);
ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    taxService = 
        ctx.getBean("taxServiceLayer", TaxServiceImpl.class);
    }
    
    
    @BeforeEach
    public void setUp() {
    }
    

//    @Test
//    public void testCreateDuplicateState() throws NoSuchStateException, TaxDuplicateStateException {
//        //arrange , just create with same product type
//       //this was craeted already in daostubimpl so expect exception
//       //arrange
//       Tax tax = new Tax();
//       
//       tax.setStateAbbrevation("ut");
//        tax.setStateName("utah");
//       tax.setTaxRate(new BigDecimal("7.9"));
//        //act
//        try{
//        taxService.createTax(tax);
//       // fail("valid state type duplicate id exception should be thrown.");
//        }catch(NoSuchStateException e){
//           fail("valid state type duplicate id exception should be thrown."); 
//        }catch(TaxDuplicateStateException e){
//            return;
//        }
//    }
//    @Test
//    public void testInvalidTax(){
//        //arrange
//       Tax tax = new Tax();
//        tax.setStateAbbrevation("ut");
//       tax.setStateName("utah");
//      tax.setTaxRate(new BigDecimal("7.9"));
//       try{
//           taxService.createTax(tax);
//       }catch( NoSuchStateException e){
//           fail("incorrect exception thrown" + e);
//       }catch(TaxDuplicateStateException e){
//           return;
//       }
//       }
        
   
    //for these two methods down use diff names like testProduct
    @Test
    public void testGetAllTaxess() throws DataPersistenceException{
        //arrange, by creating one product
        Tax testTax = new Tax();
       testTax.setStateAbbrevation("ut");
        
       testTax.setStateName("utah");
      testTax.setTaxRate(new BigDecimal("7.9"));
       //act, assert
       assertEquals(1,taxService.getAllTax().size(),"should have 1 tax");
        assertTrue(taxService.getAllTax().contains(testTax),"the one product must be marble");
        
    }
    @Test
    public void testGetTax() throws DataPersistenceException{
         Tax testTax= new Tax();
       testTax.setStateAbbrevation("ut");
      testTax.setStateName("utah");
      testTax.setTaxRate(new BigDecimal("7.9"));
       
       //act ,assert
      Tax shouldBeUt = new Tax();
       shouldBeUt = (taxService.getTax("ut"));
       assertNotNull(shouldBeUt, " getting ut should not be null");
       assertEquals(testTax,shouldBeUt,"state type must be ut for both tax objs");
       Tax shouldBeNull = new Tax();
       shouldBeNull = (taxService.getTax("chocholate"));
       assertNull(shouldBeNull,"getting chocholate should be null");
       
    }
}
