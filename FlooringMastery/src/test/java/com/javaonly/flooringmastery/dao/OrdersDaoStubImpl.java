/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.service.DuplicateOrderNumberException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class OrdersDaoStubImpl implements OrdersDao{
    //2 constructors here
    public Orders onlyOrders;
    String onlyOrderDate ;
    int number =2;
    public OrdersDaoStubImpl(){
        
       onlyOrders = new Orders();
       onlyOrders.setOrderNumber(1);
       onlyOrders.setCustomerName("tom");
        
        onlyOrders.setArea(new BigDecimal("110.0"));
        onlyOrders.setProductType("marble");
       onlyOrders.setCostPerSquareFoot(new BigDecimal("3.48"));
        onlyOrders.setLaborCostPerSquareFoot(new BigDecimal("11.78"));
       onlyOrders.setState("ut");
        onlyOrders.setTaxValues(new BigDecimal("7.9"));
        onlyOrders.setMaterialCost(new BigDecimal("382.8"));
        onlyOrders.setTotalLaborCost(new BigDecimal("1295.8"));//867.1682
        onlyOrders.setTotalTax(new BigDecimal("867.16"));
        onlyOrders.setTotalOrderCost(new BigDecimal("2545.76"));//2545.76
        onlyOrderDate = "12292021";
       
    }
    public OrdersDaoStubImpl(Orders testOrders){
     this.onlyOrders = testOrders;  
    }

    @Override
//    public Orders addOrder(String date,int number, Orders orderInfo) throws DataPersistenceException, NoSuchProductException, NoSuchStateException, 
//            NoSuchOrderException, IndexOutOfBoundException {
     public Orders addOrder(String date, Orders orderInfo) throws DataPersistenceException {
        if(number != onlyOrders.getOrderNumber() ){
            return onlyOrders;
        }else{
            return null;
        }
    }

    @Override
    public boolean editOrder(String date,int orderNumber, Orders order) throws DataPersistenceException {
       if(onlyOrderDate.equalsIgnoreCase(date)||orderNumber==onlyOrders.getOrderNumber() ){
            return true;
        }else{
            return false;
        } 
    }

    @Override
    public List<Orders> getAllOrders(String date) throws DataPersistenceException{
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(onlyOrders);
        return ordersList;
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orders removeOrder(String date, int numberAsString) throws DataPersistenceException {
        if(onlyOrderDate.equalsIgnoreCase(date)||numberAsString==onlyOrders.getOrderNumber()){
            return onlyOrders;
        }else{
            return null;
        }
    }

    @Override
    public Orders getOneOrder(String date, int orderNum) throws DataPersistenceException {
         if(onlyOrderDate.equalsIgnoreCase(date) && orderNum == onlyOrders.getOrderNumber()){
             return onlyOrders;
         }else{
             return null;
         }
    }

   
}
