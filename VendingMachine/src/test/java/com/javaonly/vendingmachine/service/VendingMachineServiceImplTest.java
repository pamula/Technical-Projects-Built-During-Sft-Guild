/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.service;

import com.javaonly.vendingmachine.dao.DataPersistenceException;
import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dao.VendingMachineAuditDao;
import com.javaonly.vendingmachine.dao.VendingMachineDao;
import com.javaonly.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.enums.Money;
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
public class VendingMachineServiceImplTest {
    //this is same as App get the dao testclasses here.
    //we have a known state in daostubimpl, so get that here
    private VendingMachineService service;
    
    public VendingMachineServiceImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//        
        service = new VendingMachineServiceImpl(dao,auditDao);
//here lets use spring framework
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
service = ctx.getBean("serviceLayer" , VendingMachineServiceImpl.class);
   }
    //here aftereach, beforeall,afterall not needed
    
    @BeforeEach
    public void setUp() {
        
    }
   
    
    @Test
    public void testCreateDuplicateIdItem() throws ItemsDataValidationException{
        //arrange , just create with same id
       //this was craeted already in daostubimpl so expect exception
        Items item = new Items("chocholates");
        //item.setItemName("chocholates");
        item.setPrice(new BigDecimal("2.16"));
        item.setQuantity(4);
        //act
        try{
            service.createItem(item);
            fail("expects duplictae id exception");
        }catch(DataPersistenceException|
                NoSuchItemInInventoryException e){
            fail("incorrect exception was thrown");
            
        }catch(ItemsDuplicateIdException e){
            return;
        }
    }
    @Test
    public void testGetAllItems() throws NoSuchItemInInventoryException, DataPersistenceException{
        //arrange by creating one item
        Items itemClone = new Items("chocholates");
        //itemClone.setItemName("chocholates");
        itemClone.setPrice(new BigDecimal("2.16"));
        itemClone.setQuantity(4);
        
        //act
        assertEquals(1,service.getAllItems().size(),"should have 1 item");
        assertTrue(service.getAllItems().contains(itemClone),"the one item must be chocholates");
    }
   
    @Test
    public void testBuyItemExactMoney()throws Exception{
        //arrange by creating one item
        Items itemClone = new Items("chocholates");
       // itemClone.setItemName("chocholates");
        itemClone.setPrice(new BigDecimal("2.16"));
        itemClone.setQuantity(4);
//        
        Items itemShouldBeChocholate = service.getItem("chocholates");
        BigDecimal actualBills = service.checkingUserBill("chocholates");
        BigDecimal userBills = new BigDecimal("2.16");
//        
        assertTrue(itemClone.getPrice().compareTo(userBills) == 0 , "both price same");
        String afterBuyingItem = service.buyItemEqual(itemClone.getItemName(), userBills);

        assertNotEquals(itemShouldBeChocholate,itemClone.getQuantity(),"since bought item here quantity must be 3");
        
        
    }
    @Test
    public void testBuyItemWithMoreMoneyReturnChange()throws Exception{
        //arrange by creating one item
        Items itemClone = new Items("chocholates");
        //itemClone.setItemName("chocholates");
        itemClone.setPrice(new BigDecimal("2.16"));
        itemClone.setQuantity(4);
//        
        Items itemShouldBeChocholate = service.getItem("chocholates");
        BigDecimal actualBills = service.checkingUserBill("chocholates");
        BigDecimal userBills = new BigDecimal("3.16");
//        
        assertTrue(itemClone.getPrice().compareTo(userBills) == -1 , "user gave more money");
        String afterBuyingItem = service.buyItemEqual(itemClone.getItemName(), userBills);

        assertNotEquals(itemShouldBeChocholate,itemClone.getQuantity(),"since bought item here quantity must be 3");
        
        
    }
    @Test
    public void testBuyItemWithLessMoney()throws Exception{
        //arrange by creating one item
        Items itemClone = new Items("chocholates");
        //itemClone.setItemName("chocholates");
        itemClone.setPrice(new BigDecimal("2.16"));
        itemClone.setQuantity(4);
        
        Items itemShouldBeChocholate = service.getItem("chocholates");
        BigDecimal actualBills = service.checkingUserBill("chocholates");
        BigDecimal userBills = new BigDecimal("1.16");
//        
assertTrue(itemClone.getPrice().compareTo(userBills) == 1 , "user gave less money ");
        String afterBuyingItem = service.buyItemEqual(itemClone.getItemName(), userBills);

        assertEquals(4,itemClone.getQuantity(),"since didnt buy item here quantity must be 4");
        
        
    }
    @Test
    public void testExtraMoneyToReturn() throws NoSuchItemInInventoryException, ItemsDataValidationException{
        BigDecimal value = new BigDecimal("0.89");
//quarters=cents/25;dime=(cents %= 25) / 10;nickle=(cents %= 10) / 5;penny=cents%1
//        
      Double dob = value.doubleValue();
//      //Excepted Results
        int cents = (int) Math.round(dob * 100);
       int quarters = cents/25; //3
       int dime =  (cents%=25)/10; //1
       int nickle = (cents %= 10) / 5; //0
       int penny = cents%1; //4
//       //Actutal Results
       int afterCalQuarter = service.returnChange(Money.QUARTER, value);
       int afterCalDime = service.returnChange(Money.DIME, value);
       int afterCalNickle = service.returnChange(Money.NICKEL, value);
       int afterCalPenny = service.returnChange(Money.PENNY, value);
        //Compare Expected to Actual
       assertEquals(quarters,afterCalQuarter,"checking given money to quarters here ");
       assertEquals(dime,afterCalDime,"checking for dime values");
       assertEquals(nickle,afterCalNickle,"checking given money to nickle here ");
       assertEquals(penny,afterCalPenny,"checking given money in penny here");
        
    }
    
}
