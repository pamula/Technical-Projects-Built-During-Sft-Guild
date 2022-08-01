/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import com.javaonly.flooringmastery.service.DuplicateOrderNumberException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface OrdersDao {
//    
     Orders addOrder(String date, Orders orderInfo)throws DataPersistenceException,NoSuchOrderException  ;
     boolean editOrder( String date,int orderNumber,Orders order)throws DataPersistenceException,NoSuchOrderException ;
     List<Orders> getAllOrders(String date)throws DataPersistenceException,NoSuchOrderException ;
     Orders removeOrder(String date , int numberAsString)throws DataPersistenceException,NoSuchOrderException;
     
   Orders getOneOrder(String date, int orderNum)throws DataPersistenceException,NoSuchOrderException ;
  
}
