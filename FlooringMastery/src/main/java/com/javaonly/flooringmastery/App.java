/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery;


import com.javaonly.flooringmastery.controller.OrdersController;
import com.javaonly.flooringmastery.dao.OrderAuditDao;
import com.javaonly.flooringmastery.dao.OrderAuditDaoImpl;
import com.javaonly.flooringmastery.dao.OrdersDao;
import com.javaonly.flooringmastery.dao.OrdersDaoFileImpl;
import com.javaonly.flooringmastery.dao.ProductsAuditDao;
import com.javaonly.flooringmastery.dao.ProductsAuditDaoImpl;
import com.javaonly.flooringmastery.dao.ProductsDao;
import com.javaonly.flooringmastery.dao.ProductsDaoFileImpl;
import com.javaonly.flooringmastery.dao.TaxAuditDao;
import com.javaonly.flooringmastery.dao.TaxAuditDaoImpl;
import com.javaonly.flooringmastery.dao.TaxDao;
import com.javaonly.flooringmastery.dao.TaxDaoFileImpl;
import com.javaonly.flooringmastery.service.OrdersService;
import com.javaonly.flooringmastery.service.OrdersServiceImpl;
import com.javaonly.flooringmastery.service.ProductsService;
import com.javaonly.flooringmastery.service.ProductsServiceImpl;
import com.javaonly.flooringmastery.service.TaxService;
import com.javaonly.flooringmastery.service.TaxServiceImpl;
import com.javaonly.flooringmastery.ui.FlooringMasteryView;
import com.javaonly.flooringmastery.ui.UserIo;
import com.javaonly.flooringmastery.ui.UserIoConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Prathima
 */
public class App {
    public static void main(String[] args){

       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       OrdersController myController = ctx.getBean("controller",OrdersController.class);
       myController.run();
       
    }
}
