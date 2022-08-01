/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dao;

import com.javaonly.vendingmachine.dto.Items;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    public Items onlyItem;
    
    public VendingMachineDaoStubImpl(){
        onlyItem = new Items("doritos");
        //onlyItem.setItemName("doritos");
        onlyItem.setPrice(new BigDecimal("2.16"));
        onlyItem.setQuantity(4);
    }
    
    public VendingMachineDaoStubImpl(Items testItem){
        this.onlyItem = testItem;
    }

    @Override
    public Items addItem(String itemNameIsId, Items item) throws DataPersistenceException {
        if(itemNameIsId.equals(onlyItem.getItemName())){
           return onlyItem;
       }else{
           return null;
       }
    }

    @Override
    public List<Items> getAllItems() throws DataPersistenceException {
       List<Items> itemsList = new ArrayList<>();
       itemsList.add(onlyItem);
       return itemsList;
    }

    @Override
    public Items getItem(String itemNameIsId) throws NoSuchItemInInventoryException {
       if(itemNameIsId.equals(onlyItem.getItemName())){
           return onlyItem;
       }else{
           return null;
       }
    }



    @Override
    public void editItem(String itemName) throws DataPersistenceException {
       if(itemName.equalsIgnoreCase(onlyItem.getItemName())){
           int value = onlyItem.getQuantity();
          int changedValue = value - 1;
         onlyItem.setQuantity(changedValue);
       }
    }

   

    @Override
    public BigDecimal checkBill(String itemName) {
         //BigDecimal itemValue = new BigDecimal(0);
         if(itemName.equalsIgnoreCase(onlyItem.getItemName())){
             return onlyItem.getPrice();
         }else{
             return null;
         }
         
    }

   
    
}
