/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
//import com.javaonly.flooringmastery.dao.IndexOutOfBoundException;
//import com.javaonly.flooringmastery.dao.NoSuchDateException;
import com.javaonly.flooringmastery.dao.NoSuchOrderException;
//import com.javaonly.flooringmastery.dao.NoSuchProductException;
import com.javaonly.flooringmastery.dao.NoSuchStateException;
import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface OrdersService {

   void createOrder(String date ,Orders order)throws DataPersistenceException , DuplicateOrderNumberException,
            OrdersDataValidationException ,NoSuchOrderException ;
   
  List<Orders> getAllOrders( String date) throws DataPersistenceException,NoSuchOrderException  ;
         
    boolean editOrder(String date, int orderNumber, Orders order)  throws DataPersistenceException , 
           DuplicateOrderNumberException ,
            OrdersDataValidationException,NoSuchOrderException ; 
   
  Orders removeOrder( String date,int numberAsString)throws DataPersistenceException,NoSuchOrderException  ;
           
    Orders getAnOrder(String date, int orderNumber)throws DataPersistenceException,NoSuchOrderException  ; 
  
}
