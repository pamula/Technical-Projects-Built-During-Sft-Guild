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
import com.javaonly.flooringmastery.dao.OrderAuditDao;
import com.javaonly.flooringmastery.dao.OrdersDao;
import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class OrdersServiceImpl implements OrdersService {

    private OrderAuditDao auditDao;
    private OrdersDao dao;

    public OrdersServiceImpl(OrdersDao dao, OrderAuditDao auditDao) {

        this.dao = dao;
        this.auditDao = auditDao;
    }

    private void validateOrderData(Orders order) throws OrdersDataValidationException {
        //orderdate,customername,producttype,state,area
        if (order.getCustomerName() == null || order.getCustomerName().trim().length() == 0
                || order.getArea() == BigDecimal.ZERO
                || order.getProductType() == null || order.getProductType().trim().length() == 0
                || order.getState() == null || order.getState().trim().length() == 0
                || order.getTaxValues() == BigDecimal.ZERO || order.getCostPerSquareFoot() == BigDecimal.ZERO
                || order.getLaborCostPerSquareFoot() == BigDecimal.ZERO) {

            throw new OrdersDataValidationException("ERROR ALL FIELDS[DATE,CUSTOMERNAME,PRODUCTTYPE] REQUIRED");

        }
    }

    @Override

    public void createOrder(String date, Orders order) throws DataPersistenceException, DuplicateOrderNumberException,
            OrdersDataValidationException, NoSuchOrderException {


        validateOrderData(order);

        dao.addOrder(date, order);

        auditDao.writeAuditEntry("Order" + date + "created");

    }

    @Override
    public List<Orders> getAllOrders(String date) throws DataPersistenceException, NoSuchOrderException {

        return dao.getAllOrders(date);
    }

    @Override
    public boolean editOrder(String date, int numberAsString, Orders order) throws DataPersistenceException, DuplicateOrderNumberException,
            OrdersDataValidationException, NoSuchOrderException {

        validateOrderData(order);
        for (Orders allOrders : dao.getAllOrders(date)) {
            if (allOrders.getOrderNumber() == numberAsString) {

                dao.editOrder(date, numberAsString, order);
            }
        }
        return true;

    }

    @Override
    public Orders getAnOrder(String date, int orderNumber) throws DataPersistenceException, NoSuchOrderException {

        return dao.getOneOrder(date, orderNumber);

    }

    @Override
    public Orders removeOrder(String date, int numberAsString) throws DataPersistenceException, NoSuchOrderException {
        Orders order = null;
        for (Orders allOrders : dao.getAllOrders(date)) {
            if (allOrders.getOrderNumber() == numberAsString) {

                order =  dao.removeOrder(date, numberAsString);

            }

        }
return order;
    }
}
