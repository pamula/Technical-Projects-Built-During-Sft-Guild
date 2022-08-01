/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dao;

import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.service.UnderPaidPriceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final String ITEM_FILE;
    public static final String DELIMETER = "::";

    public VendingMachineDaoFileImpl() {
        ITEM_FILE = "item.txt";
    }

    public VendingMachineDaoFileImpl(String itemTextFile) {
        ITEM_FILE = itemTextFile;
    }

    private Map<String, Items> items = new HashMap<>();
    
    @Override
    public Items addItem(String itemName, Items item) throws DataPersistenceException {
        loadItem();
        Items newItem = items.put(itemName, item);
        try {
            writeItem();
        } catch (NoSuchItemInInventoryException ex) {
            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newItem;
    }

//    @Override
//    public Items addItem(String itemNameIsId, Items item) throws DataPersistenceException {
//        loadItem();
//        Items newItem = items.put(itemNameIsId, item);
//        try {
//            writeItem();
//        } catch (NoSuchItemInInventoryException ex) {
//            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return newItem;
//    }

    @Override
    public List<Items> getAllItems() throws DataPersistenceException, NoSuchItemInInventoryException {
        if (items.keySet() != null) {
            loadItem();
        }
        return new ArrayList(items.values());

    }

    @Override
    public Items getItem(String itemName) throws NoSuchItemInInventoryException {
        return items.get(itemName);
    }

    //since our application looking for name of item and cost
    //and after buying u need to change cost only,
    //this helps in editing cost on item
    
    @Override
    public void editItem(String itemName) throws DataPersistenceException {
        for (Items allItems : items.values()) {
            if (allItems.getItemName().equalsIgnoreCase(itemName)) {
                int value = allItems.getQuantity();
                int changedValue = value - 1;
                allItems.setQuantity(changedValue);
                try {
                    writeItem();
                } catch (NoSuchItemInInventoryException ex) {
                    Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //this method is helpful in srvice to compare userbill to actual value in dao list
    @Override
    public BigDecimal checkBill(String itemName) throws UnderPaidPriceException {

        BigDecimal itemValue = new BigDecimal(0);

        for (Items allItems : items.values()) {

            if (allItems.getItemName().equalsIgnoreCase(itemName)) {
                itemValue = allItems.getPrice();
            }
        }
        return itemValue;
    }

    private Items unmarshallItem(String itemAsText) {

        String[] itemTokens = itemAsText.split(DELIMETER);

        // Given the pattern above, the item name is in index 0 of the array.
        String itemName = itemTokens[0];

        Items itemFromFile = new Items(itemName);
        //itemFromFile.setItemName(itemName);

        itemFromFile.setPrice(new BigDecimal(itemTokens[1]));

        itemFromFile.setQuantity(Integer.parseInt(itemTokens[2]));

        // We have now created a item! Return it!
        return itemFromFile;
    }

    private void loadItem() throws DataPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new DataPersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine ;
        // currentItem holds the most recent item unmarshalled
        Items currentItem;
        
        //here this code until else will skip the first line in txt file
            //wont get error while unmarshall the file
            if (scanner.hasNext() == true) {
                scanner.nextLine();
            } 
//            else {
//                System.out.println("Error: File is empty");
//
//            }

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();

            currentItem = unmarshallItem(currentLine);
             items.put(currentItem.getItemName(), currentItem);

//            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private String marshallItem(Items aItem) {
        // We need to turn a Items object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // coke::2.0::1

        // Start with the item id, since that's supposed to be first.
       // String itemAsText = aItem.getItemId() + DELIMETER;
String itemAsText = aItem.getItemName() + DELIMETER;
        // add the rest of the properties in the correct order:
        //itemAsText += aItem.getItemName() + DELIMETER;

        //price
        itemAsText += aItem.getPrice() + DELIMETER;

        // quantity
        itemAsText += aItem.getQuantity();

        
        return itemAsText;
    }

    private void writeItem() throws DataPersistenceException, NoSuchItemInInventoryException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new DataPersistenceException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        String header = "item, value1, value2";
        List<Items> itemList = this.getAllItems();
        //out.write(header + "\n");
        for (Items currentItem : itemList) {
            // turn a Item into a String
            itemAsText =  marshallItem(currentItem);
            // write the Item object to the file
            out.println( itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
