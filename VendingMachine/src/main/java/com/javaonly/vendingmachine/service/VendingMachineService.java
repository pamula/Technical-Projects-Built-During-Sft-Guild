/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.service;

import com.javaonly.vendingmachine.dao.DataPersistenceException;
import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.enums.Money;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface VendingMachineService {

    void createItem(Items item) throws DataPersistenceException, NoSuchItemInInventoryException,
            ItemsDuplicateIdException, ItemsDataValidationException;

    List<Items> getAllItems() throws NoSuchItemInInventoryException, DataPersistenceException;

    Items getItem(String itemName) throws NoSuchItemInInventoryException;

    String buyItemEqual(String itemName, BigDecimal bills) throws DataPersistenceException,
            UnderPaidPriceException, NoSuchItemInInventoryException, ItemsDataValidationException;

    String buyItemWithChange(String itemName, BigDecimal bills) throws DataPersistenceException,
            UnderPaidPriceException, NoSuchItemInInventoryException, ItemsDataValidationException;

    boolean userItemInput(String itemName) throws DataPersistenceException,
            NoSuchItemInInventoryException;

    BigDecimal checkingUserBill(String itemName) throws ItemsDataValidationException;

    public int returnChange(Money money, BigDecimal value);

    String buyItemWithLessInput(String itemName, BigDecimal bills) throws DataPersistenceException,
            NoSuchItemInInventoryException;

}
