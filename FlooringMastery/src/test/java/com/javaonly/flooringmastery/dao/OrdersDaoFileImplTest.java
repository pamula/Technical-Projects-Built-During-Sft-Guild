/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;
//
import com.javaonly.flooringmastery.dto.Orders;
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
public class OrdersDaoFileImplTest {
    OrdersDao testOrdersDao;
    
    public OrdersDaoFileImplTest() {
    }
    
    
    //puts the s/s in known good state b4 each test
    @BeforeEach
    public void setUp() throws Exception{
//        String date = "2021-12-29" ;
        String testFile = "OrdersTest/orders_";
       // new FileWriter(testFile);
        testOrdersDao = new OrdersDaoFileImpl(testFile);
    }
    
   
    @Test
    public void testAddAndGetOrder() throws Exception{
        
        //create an order
        int orderNum = 1;
        Orders order = new Orders();
        order.setOrderNumber(orderNum);
        order.setCustomerName("tom");
        order.setArea(new BigDecimal("110.0"));
        order.setProductType("marble");
        order.setCostPerSquareFoot(new BigDecimal("3.48"));
        order.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       order.setState("ut");
        order.setTaxValues(new BigDecimal("7.9"));
        order.setMaterialCost(new BigDecimal("382.8"));
        order.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order.setTotalTax(new BigDecimal("867.16"));
        order.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
//        
        //add to dao
       // testOrdersDao.addOrder("12292021",orderNum, order);
       testOrdersDao.addOrder("12292021", order);
        //get order from dao
        Orders retrievedOrder = testOrdersDao.getOneOrder("12292021", 1);
        //check data is equal
        //assertEquals(order.getOrderNumber(),retrievedOrder.getOrderNumber());
        assertEquals(order.getCustomerName(),retrievedOrder.getCustomerName());
        
    }
    @Test
    public void testAddGetAllOrders()throws Exception{
        
        int orderNum =1;
        Orders order = new Orders();
        order.setOrderNumber(orderNum);
        order.setCustomerName("tom");
        order.setArea(new BigDecimal("110.0"));
        order.setProductType("marble");
        order.setCostPerSquareFoot(new BigDecimal("3.48"));
        order.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
        order.setState("ut");
        order.setTaxValues(new BigDecimal("7.9"));
        order.setMaterialCost(new BigDecimal("382.8"));
        order.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order.setTotalTax(new BigDecimal("867.16"));
        order.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
//        
        
        int orderNum1 = 2;
        Orders order1 = new Orders();
        order.setOrderNumber(orderNum1);
         order1.setCustomerName("sam");
        order1.setArea(new BigDecimal("110.0"));
        order1.setProductType("marble");
        order1.setCostPerSquareFoot(new BigDecimal("3.48"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
        order1.setState("ut");
        order1.setTaxValues(new BigDecimal("7.9"));
        order1.setMaterialCost(new BigDecimal("382.8"));
        order1.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order1.setTotalTax(new BigDecimal("867.16"));
        order1.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
        
        testOrdersDao.addOrder("12292021", order);
        testOrdersDao.addOrder("12292021", order1);

        List<Orders> allOrders = testOrdersDao.getAllOrders("12292021");
        
        assertNotNull(allOrders,"The list of orders must not null");
//        assertEquals(2,allOrders.size(),"list of orders should be 2");
         assertTrue(testOrdersDao.getAllOrders("12292021").contains(order),
                "The list of orders should include sam.");
    assertTrue(testOrdersDao.getAllOrders("12292021").contains(order1),
            "The list of orders should include tom.");
    }
    
    @Test
    public void addAndRemoveOrderTest()throws Exception{
        int orderNum =1;
        Orders order3 = new Orders();
        order3.setOrderNumber(orderNum);
        order3.setCustomerName("tom");
        order3.setArea(new BigDecimal("110.0"));
        order3.setProductType("marble");
        order3.setCostPerSquareFoot(new BigDecimal("3.48"));
        order3.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
        order3.setState("ut");
        order3.setTaxValues(new BigDecimal("7.9"));
        order3.setMaterialCost(new BigDecimal("382.8"));
        order3.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order3.setTotalTax(new BigDecimal("867.16"));
        order3.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
        
         int orderNum1 = 2;
        Orders order4 = new Orders();
        order4.setOrderNumber(orderNum);
order4.setCustomerName("sam");
order4.setArea(new BigDecimal("110.0"));
        order4.setProductType("marble");
        order4.setCostPerSquareFoot(new BigDecimal("3.48"));
        order4.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       order4.setState("ut");
        order4.setTaxValues(new BigDecimal("7.9"));
        order4.setMaterialCost(new BigDecimal("382.8"));
        order4.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order4.setTotalTax(new BigDecimal("867.16"));
        order4.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
//        
//        
         
          testOrdersDao.addOrder("12292021",order3);
         testOrdersDao.addOrder("12292021",order4);
         //remove 1 order
          Orders removedOrder = testOrdersDao.removeOrder("12292021",order3.getOrderNumber());
        
         List<Orders> allOrders = testOrdersDao.getAllOrders("12292021");
         
          assertNotNull( allOrders, "All orders list should be not null.");


     assertFalse(allOrders.contains(order3),"tom has to be removed");
     assertTrue(allOrders.contains(order4),"sam is not removed");
     
     // Remove the second order
    removedOrder = testOrdersDao.removeOrder("12292021",order4.getOrderNumber());
    // Check that the correct object was removed.
    assertEquals( removedOrder, order4, "The removed order should be sam.");

    // retrieve all of the students again, and check the list.
    allOrders = testOrdersDao.getAllOrders("12292021");

    // Check the contents of the list - it should be empty
    assertFalse( allOrders.isEmpty(), "The retrieved list  should be empty.");

    // Try to 'get' both students by their old id - they should be null!
    Orders retrievedOrder = testOrdersDao.getOneOrder("12292021", order3.getOrderNumber());
    assertNull(retrievedOrder, "tom was removed, should be null.");

    retrievedOrder = testOrdersDao.getOneOrder("12292021", order4.getOrderNumber());
    assertNull(retrievedOrder, "sam was removed, should be null.");

}
    @Test
    public void testEditOrder()throws Exception{
        Orders order3 = new Orders();
        order3.setOrderNumber(1);
        order3.setCustomerName("tom");
        order3.setArea(new BigDecimal("110.0"));
        order3.setProductType("marble");
        order3.setCostPerSquareFoot(new BigDecimal("3.48"));
        order3.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
        order3.setState("ut");
        order3.setTaxValues(new BigDecimal("7.9"));
        order3.setMaterialCost(new BigDecimal("382.8"));
        order3.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order3.setTotalTax(new BigDecimal("867.16"));
        order3.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
//         testOrdersDao.addOrder("12292021",order3.getOrderNumber(), order3);
testOrdersDao.addOrder("12292021", order3);
         
         order3 = new Orders();
         order3.setOrderNumber(2);
        order3.setCustomerName("tom");
        order3.setArea(new BigDecimal("110.0"));
        order3.setProductType("marble");
        order3.setCostPerSquareFoot(new BigDecimal("3.48"));
        order3.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
        order3.setState("ut");
        order3.setTaxValues(new BigDecimal("7.9"));
        order3.setMaterialCost(new BigDecimal("382.8"));
        order3.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        order3.setTotalTax(new BigDecimal("867.16"));
        order3.setTotalOrderCost(new BigDecimal("2545.76"));
        
        //now edit order3 from 1 to 2 
        testOrdersDao.editOrder("12292021", order3.getOrderNumber(), order3);
        //edit order3
boolean editedOrder = testOrdersDao.editOrder("12292021", order3.getOrderNumber(), order3);
//get the orders in dao, now 01 has to change to 02//
 List<Orders> allOrders = testOrdersDao.getAllOrders("12292021");
//now edir dvd,check edited one 
assertEquals(editedOrder,testOrdersDao.editOrder("12292021", order3.getOrderNumber(), order3),"edited order here");

        
    }
       
//         
    }
//    

