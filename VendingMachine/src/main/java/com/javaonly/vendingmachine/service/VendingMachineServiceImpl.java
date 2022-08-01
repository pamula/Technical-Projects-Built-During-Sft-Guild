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
import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.enums.Money;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineAuditDao auditDao;
    private VendingMachineDao dao;
    private Money money;

    public VendingMachineServiceImpl(Money money) {
        this.money = money;
    }

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
//since service talks to dao u get all the methods from dao and apply those to service layer
    //methods
    
    
    
    @Override
    public void createItem(Items item) throws DataPersistenceException,
            NoSuchItemInInventoryException, ItemsDuplicateIdException {
        if (dao.getItem(item.getItemName()) != null) {
            throw new ItemsDuplicateIdException("Error could not create item Id"
                    + item.getItemName() + "already exists");
        }

        try {
            validateItemData(item);
        } catch (ItemsDataValidationException ex) {
            Logger.getLogger(VendingMachineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao.addItem(item.getItemName(), item);
        auditDao.writeAuditEntry("Item" + item.getItemName() + "created");

    }

//    @Override
//    public void createItem(Items item) throws DataPersistenceException,
//            NoSuchItemInInventoryException, ItemsDuplicateIdException {
//        if (dao.getItem(item.getItemId()) != null) {
//            throw new ItemsDuplicateIdException("Error could not create item Id"
//                    + item.getItemId() + "already exists");
//        }
//
//        try {
//            validateItemData(item);
//        } catch (ItemsDataValidationException ex) {
//            Logger.getLogger(VendingMachineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        dao.addItem(item.getItemId(), item);
//        auditDao.writeAuditEntry("Item" + item.getItemName() + "created");
//
//    }

    @Override
    public List<Items> getAllItems() throws NoSuchItemInInventoryException, DataPersistenceException {

        return dao.getAllItems();

    }

    @Override
    public Items getItem(String itemName) throws NoSuchItemInInventoryException {
        return dao.getItem(itemName);
    }

    //this method to prompt the user until  correct item name
    @Override
    public boolean userItemInput(String itemName) throws DataPersistenceException,
            NoSuchItemInInventoryException {
        try{
            
        
        for (Items itemsCost : dao.getAllItems()) {
            if (itemsCost.getItemName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
    }catch(DataPersistenceException |
            NoSuchItemInInventoryException e){
        System.out.println("no such item in list");
        
}
    

        return false;
    }

    @Override
    public BigDecimal checkingUserBill(String itemName) {
        BigDecimal value = new BigDecimal(0);
        try {
            value = dao.checkBill(itemName);
        } catch (NoSuchItemInInventoryException | UnderPaidPriceException ex) {
            Logger.getLogger(VendingMachineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

//this method for exact money from the user////
    //also if user gives less money throw underpaid exception and a msg///
    @Override
    public String buyItemEqual(String itemName, BigDecimal bills) {
        

try{
    int quantityOfItem = 0;
        int afterQuantityChange = 0;
        for (Items itemsCost : dao.getAllItems()) {

            if (itemsCost.getItemName().equalsIgnoreCase(itemName) && itemsCost.getPrice().compareTo(bills) == 0) {

                quantityOfItem = itemsCost.getQuantity();
                System.out.println(quantityOfItem);
                afterQuantityChange = quantityOfItem - 1;

                dao.editItem(itemName);
                auditDao.writeAuditEntry("ItemName" + itemName + "changed");
                System.out.println("u bought the item " + itemsCost.getItemName());

            }
        }
}catch(DataPersistenceException  | 
            NoSuchItemInInventoryException  e ){
    System.out.println("no such item in list");
    
}
        return itemName;
    }

    //this method for more money and return  change///
    @Override
    public String buyItemWithChange(String itemName, BigDecimal bills) {
        int quantityOfItem = 0;
        int afterQuantityChange = 0;

        try {
            for (Items itemsCost : dao.getAllItems()) {

                if (itemsCost.getItemName().equalsIgnoreCase(itemName) && itemsCost.getPrice().compareTo(bills) == -1) {

                    BigDecimal currentValue = new BigDecimal(0);
                    BigDecimal fromActualPrice = itemsCost.getPrice();
                    currentValue = bills.subtract(fromActualPrice);

                    quantityOfItem = itemsCost.getQuantity();
                    afterQuantityChange = quantityOfItem - 1;

                    dao.editItem(itemName);
                    auditDao.writeAuditEntry("ItemName" + itemName + "changed");

                    System.out.println("u bought the item " + itemsCost.getItemName() + " and change u get is : ");

                    System.out.println(returnChange(money.QUARTER, currentValue) + " quarters "
                            + returnChange(money.DIME, currentValue) + " dimes "
                            + returnChange(money.NICKEL, currentValue) + " nickles "
                            + returnChange(money.PENNY, currentValue) + " pennies " + " is the change u get ");

                }

            }
        } catch (DataPersistenceException
                | NoSuchItemInInventoryException e) {
             System.out.println("no such item in list");

        }
        return itemName;
    }
//method for less money and print extra money user to put to buy item//
    @Override
    public String buyItemWithLessInput(String itemName, BigDecimal bills) {
        int quantityOfItem = 0;
        int afterQuantityChange = 0;

        try {
            for (Items itemsCost : dao.getAllItems()) {

                if (itemsCost.getItemName().equalsIgnoreCase(itemName) && itemsCost.getPrice().compareTo(bills) == 1) {

                    BigDecimal currentValue = new BigDecimal(0);
                    BigDecimal fromActualPrice = itemsCost.getPrice();
                    currentValue = fromActualPrice.subtract(bills);

                    quantityOfItem = itemsCost.getQuantity();

                    System.out.println("u want to buy the item: " + itemsCost.getItemName() + " but need to insert  : ");

                    System.out.println(returnChange(money.QUARTER, currentValue) + " quarters "
                            + returnChange(money.DIME, currentValue) + " dimes "
                            + returnChange(money.NICKEL, currentValue) + " nickles "
                            + returnChange(money.PENNY, currentValue) + " pennies " + " to buy ");

                }

            }
        } catch (DataPersistenceException
                | NoSuchItemInInventoryException e) {
             System.out.println("no such item in list");

        }
        return itemName;

    }

    //Using enums class to display how much change user gets if they gave more money
    @Override
    public int returnChange(Money money, BigDecimal value) {

        Double dob;
        //take bigdecimal value and make doublehere
        dob = value.doubleValue();
        int cents = (int) Math.round(dob * 100);
        switch (money) {
            case QUARTER:
                return cents / 25;
            case DIME:
                return (cents %= 25) / 10;
            case NICKEL:
                return (cents %= 10) / 5;
            case PENNY:
                return cents % 1;
            default:
        }
        return 0;
    }

    private void validateItemData(Items item) throws ItemsDataValidationException {
        if ( item.getItemName() == null || item.getItemName().trim().length() == 0) {
            throw new ItemsDataValidationException("ERROR ALL FIELDS [ID,ITEMNAME,BIGDECIMAL VALUE,PRICE] REQUIRED");
        }
    }

}
