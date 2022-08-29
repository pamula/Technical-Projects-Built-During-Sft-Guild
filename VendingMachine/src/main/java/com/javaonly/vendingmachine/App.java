/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine;

import com.javaonly.vendingmachine.controller.VendingMachineController;
import com.javaonly.vendingmachine.dao.DataPersistenceException;
import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dao.VendingMachineAuditDao;
import com.javaonly.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.javaonly.vendingmachine.dao.VendingMachineDao;
import com.javaonly.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.javaonly.vendingmachine.service.ItemsDuplicateIdException;
import com.javaonly.vendingmachine.service.UnderPaidPriceException;
import com.javaonly.vendingmachine.service.VendingMachineService;
import com.javaonly.vendingmachine.service.VendingMachineServiceImpl;
import com.javaonly.vendingmachine.ui.UserIO;
import com.javaonly.vendingmachine.ui.UserIOConsoleImpl;
import com.javaonly.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *2
 * 
 * @author Prathima
 */
public class App {
    public static void main(String[] args) {
       
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
        
        
    }
}
