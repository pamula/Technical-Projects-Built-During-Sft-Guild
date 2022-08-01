/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dao;

import com.javaonly.vendingmachine.dto.Items;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
   
    public VendingMachineDaoFileImplTest() {
    }
    

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testitem.txt";
        
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testAddAndGetAllItem() throws DataPersistenceException {
        Items firstItem = new Items("doritos");
        //firstItem.setItemName("doritos");
        firstItem.setPrice(new BigDecimal("2.16"));
        firstItem.setQuantity(4);
        
////        //second item
        Items secondItem = new Items("peanuts");
       //secondItem .setItemName("cheetos");
        secondItem .setPrice(new BigDecimal("1.16"));
        secondItem .setQuantity(4);
////        
////        //now add to dao
        testDao.addItem(firstItem.getItemName(), firstItem);
        testDao.addItem(secondItem.getItemName(), firstItem);
//        
//        //we need to list all items first
//        //lets check using list getAllItems(),this is in daofileimpl
 List<Items> allItems;
try {
allItems = testDao.getAllItems();
//        
//        
//        
//        // First check the general contents of the list
assertNotNull(allItems, "The list of items must not null");
assertEquals(2, allItems.size(),"List of items should have 2 items.");
} catch (NoSuchItemInInventoryException ex) {
            Logger.getLogger(VendingMachineDaoFileImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Test
    public void testAddAndGetPriceOfOneItem() throws Exception{
         Items firstItem = new Items("doritos");
        //firstItem.setItemName("doritos");
        firstItem.setPrice(new BigDecimal("2.16"));
        firstItem.setQuantity(4);//        
//        //second item
        Items secondItem = new Items("peanuts");
        //secondItem .setItemName("cheetos");
        secondItem .setPrice(new BigDecimal("1.16"));
        secondItem .setQuantity(4);
//        
//        //now add to dao
        testDao.addItem(firstItem.getItemName(), firstItem);
        testDao.addItem(secondItem.getItemName(), firstItem);
        //now edit one value
        BigDecimal priceOfItem = testDao.checkBill(firstItem.getItemName());
        assertEquals(firstItem.getPrice(),priceOfItem, " both price are equal before the item is bought ");
        
    }
    @Test
    public void checkUserBill()throws Exception{
       Items firstItem = new Items("doritos");
       //firstItem.setItemName("doritos");
        firstItem.setPrice(new BigDecimal("2.16"));
        firstItem.setQuantity(4);
         testDao.addItem(firstItem.getItemName(), firstItem);
         BigDecimal priceOfItem = testDao.checkBill(firstItem.getItemName());
        assertEquals(firstItem.getPrice(),priceOfItem, " both price are equal before the item is bought ");
        
        
    }
    
}
