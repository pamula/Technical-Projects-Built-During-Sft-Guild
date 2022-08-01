/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;

import com.javaonly.flooringmastery.dao.NoSuchOrderException;
import com.javaonly.flooringmastery.service.DuplicateOrderNumberException;
import com.javaonly.flooringmastery.dao.NoSuchStateException;
import com.javaonly.flooringmastery.dao.OrderAuditDao;
import com.javaonly.flooringmastery.dao.OrdersDao;
import com.javaonly.flooringmastery.dao.OrdersDaoStubImpl;
import com.javaonly.flooringmastery.dto.Orders;
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
public class OrdersServiceImplTest {
    private OrdersService ordersService;
    
    public OrdersServiceImplTest() {
//        OrdersDao ordersDao = new OrdersDaoStubImpl();
//        OrderAuditDao ordersAuditDao = new OrderAuditDaoStubImpl();
//        ordersService = new OrdersServiceImpl(ordersDao, ordersAuditDao);
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
ordersService = ctx.getBean("serviceLayer", OrdersService.class);

    }
    
    
    @BeforeEach
    public void setUp() {
    }
    

    @Test
    public void testCreateValidOrder() throws Exception{
        //arrange
        
        Orders order = new Orders();
        order.setOrderNumber(3);
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
     
     try{
         ordersService.createOrder("12292021",order);
     }catch(DataPersistenceException e){
    fail("order valid . no exception here");
    return;
     }
    
}
    @Test
    public void testDuplicateOrder()throws Exception{
        Orders order = new Orders();
        order.setOrderNumber(1);
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
        try{
            ordersService.createOrder("12292021",order);
//            fail("Expected DupeId Exception was not thrown");
        }catch(DataPersistenceException e){
            fail("incorrect exception thrown , datavalidation error here");
        }catch(DuplicateOrderNumberException   e){
           return; 
        }
    }
    @Test
    public void testGetAllOrders() throws Exception{
        Orders testOrder = new Orders();
        testOrder.setOrderNumber(1);
       testOrder.setCustomerName("tom");
       
        testOrder.setArea(new BigDecimal("110.0"));
        testOrder.setProductType("marble");
       testOrder.setCostPerSquareFoot(new BigDecimal("3.48"));
        testOrder.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       testOrder.setState("ut");
        testOrder.setTaxValues(new BigDecimal("7.9"));
        testOrder.setMaterialCost(new BigDecimal("382.8"));
        testOrder.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        testOrder.setTotalTax(new BigDecimal("867.16"));
        testOrder.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
        
        assertEquals(1,ordersService.getAllOrders("12292021").size(),"should have 1 order");
    assertTrue(ordersService.getAllOrders("12292021").contains(testOrder),"one order must be tom");
    
    }
    @Test
    public void testGetOrder() throws Exception{
        Orders testOrder = new Orders();
        testOrder.setOrderNumber(1);
       testOrder.setCustomerName("tom");
       
        testOrder.setArea(new BigDecimal("110.0"));
        testOrder.setProductType("marble");
       testOrder.setCostPerSquareFoot(new BigDecimal("3.48"));
        testOrder.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       testOrder.setState("ut");
        testOrder.setTaxValues(new BigDecimal("7.9"));
        testOrder.setMaterialCost(new BigDecimal("382.8"));
        testOrder.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        testOrder.setTotalTax(new BigDecimal("867.16"));
        testOrder.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
       
        ordersService.createOrder("12292021", testOrder);
        //act and assert
        Orders shouldBeTom = ordersService.getAnOrder("12292021", testOrder.getOrderNumber());
        assertNotNull(shouldBeTom,"getting 1 should not be null");
        assertEquals(testOrder,shouldBeTom,"order stored under 1 is tom");
        shouldBeTom = ordersService.removeOrder("12292021", 1);
        
        Orders shouldBeNull = ordersService.getAnOrder("12312021", 42);
        assertNull(shouldBeNull,"getting 42 must be null");
        
    }
    @Test
    public void testRemoveOrder()throws Exception{
        //arrange
        Orders testOrder = new Orders();
        testOrder.setOrderNumber(1);
       testOrder.setCustomerName("tom");
       
        testOrder.setArea(new BigDecimal("110.0"));
        testOrder.setProductType("marble");
       testOrder.setCostPerSquareFoot(new BigDecimal("3.48"));
        testOrder.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       testOrder.setState("ut");
        testOrder.setTaxValues(new BigDecimal("7.9"));
        testOrder.setMaterialCost(new BigDecimal("382.8"));
        testOrder.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        testOrder.setTotalTax(new BigDecimal("867.16"));
        testOrder.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
        
        //act and assert
        Orders shouldBeTom = ordersService.removeOrder("12292021", testOrder.getOrderNumber());
         assertEquals( testOrder, shouldBeTom, "Order removed from 1 should be Tom.");

    Orders shouldBeNull = ordersService.removeOrder("12202134",8);    
    assertNull( shouldBeNull, "Removing 8 should be null.");
    }
    
    
}