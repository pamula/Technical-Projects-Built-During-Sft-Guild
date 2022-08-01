/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dao;

import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.service.UnderPaidPriceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface VendingMachineDao {

    Items addItem(String itemNameIsId, Items item) throws DataPersistenceException;

    List<Items> getAllItems() throws DataPersistenceException, NoSuchItemInInventoryException;

    Items getItem(String itemName) throws NoSuchItemInInventoryException;

    void editItem(String itemName) throws DataPersistenceException;

    BigDecimal checkBill(String itemName) throws NoSuchItemInInventoryException, UnderPaidPriceException;

}
