/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.ui;

import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.service.ItemsDataValidationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
//import java.util.Scanner;

/**
 *
 * @author Prathima
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    //for user to make choice to buy item or exit
    public int printMenuAndGetSelection() {

        io.print("1.Do U want to buy an item? ");
        io.print("2.Exit ");

        return io.readInt("please select from the above choice, wanna buy item/exit?",1,2);
    }

    public void displayCreateItemBanner() {
        io.print("===== Create Item =======");
    }
//    
    //used this method to get item info to create item list(at first)

    public Items getNewItemInfo() {

        String itemId = io.readString("please enter item Id");
        int quantity = io.readInt("please enter quantity");
        BigDecimal price = io.readBigDecimal("please enter price of the item in bigdecimal");
        String itemName = io.readString("please enter item name");

        Items currentItem = new Items(itemName);
//        currentItem.setItemName(itemName);
        currentItem.setPrice(price);
        currentItem.setQuantity(quantity);

        return currentItem;

    }

    public Items getNewEditedItemToChange() {
        /*
       id; itemname; itemprice; itemquantity; 
    
         */
        String itemId = io.readString("please enter item ID");

        String itemNAME = io.readString("please enter name");
        BigDecimal itemQuantity = io.readBigDecimal("please enter price");
        int quantity = io.readInt("please enter quantity of item");

        Items currentEditedItem = new Items(itemNAME);
        //currentEditedItem.setItemName(itemNAME);
        currentEditedItem.setQuantity(quantity);
        currentEditedItem.setPrice(itemQuantity);

        return currentEditedItem;

    }

    public void displayCreateSuccessBanner() {
        io.readString("Item successfully created please hit enter to continue");
    }

    //to get userchoice itemname
    public String printItemToBuy() {
        return io.readString("please choose an item to buy");
    }

    //to get bills from user
    public BigDecimal printUserBill() throws ItemsDataValidationException {

        
        return io.readBigDecimal("please insert bills to buy an item");
    }

    //***********************************************************
    public void displayDisplayAllBanner() {
        io.print("==== Display All items ====");
    }

    //to display item list
    public void displayItemList(List<Items> itemsList) {
        for (Items currentItem : itemsList) {
            if (currentItem.getQuantity() > 0) {
//                String itemInfo = String.format("#%s : %s %s %s", currentItem.getItemId(), currentItem.getItemName(),
//                        currentItem.getPrice(), currentItem.getQuantity());
String itemInfo = String.format("#%s : %s %s ",  currentItem.getItemName(),
                        currentItem.getPrice(), currentItem.getQuantity());
                io.print(itemInfo);
            }
//           
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    public void displayExitBanner() {
        io.print("good bye");
    }

    public void displayUnknownCommandBanner() {
        io.print("unknown command");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
