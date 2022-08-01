/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.controller;

import com.javaonly.vendingmachine.dao.DataPersistenceException;
import com.javaonly.vendingmachine.dao.NoSuchItemInInventoryException;
import com.javaonly.vendingmachine.dto.Items;
import com.javaonly.vendingmachine.service.ItemsDataValidationException;
import com.javaonly.vendingmachine.service.ItemsDuplicateIdException;
import com.javaonly.vendingmachine.service.UnderPaidPriceException;
import com.javaonly.vendingmachine.service.VendingMachineService;
import com.javaonly.vendingmachine.ui.UserIO;
import com.javaonly.vendingmachine.ui.UserIOConsoleImpl;
import com.javaonly.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class VendingMachineController {

    //controller talks to service, view ,  userio 
    private VendingMachineView view;
    private VendingMachineService service;
    private UserIO io = new UserIOConsoleImpl();

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        //try catch block + loop to repeat things
        //Need some way to exit
        boolean keepGoing = true;
        listItems();
        try {
            

            int menuSelection = 0;
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        listItems();
                        buyItemFromMachine();
                        break;
                    case 2:
                        keepGoing = false;
                       
                        break;
                       
                    default:
                        unknownCommand();
                }
            }
             exitMessage();

        } catch (ItemsDataValidationException | DataPersistenceException | UnderPaidPriceException
                | NoSuchItemInInventoryException ex) {
            view.displayErrorMessage(ex.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


    private void listItems() {
        view.displayDisplayAllBanner();
        try {
            List<Items> itemList = service.getAllItems();
            view.displayItemList(itemList);
        } catch (NoSuchItemInInventoryException | DataPersistenceException e) {
            view.displayErrorMessage("no such item ");
        }
    }

    private void buyItemFromMachine() throws ItemsDataValidationException,
            DataPersistenceException, UnderPaidPriceException, NoSuchItemInInventoryException {
        try {
            BigDecimal userGIvenBIll = new BigDecimal(0);
            String userPickItem = null;
            userGIvenBIll = view.printUserBill();
            do {

                userPickItem = view.printItemToBuy();

                if (service.checkingUserBill(userPickItem).compareTo(userGIvenBIll) == 0) {
                    service.buyItemEqual(userPickItem, userGIvenBIll);
                } else if (service.checkingUserBill(userPickItem).compareTo(userGIvenBIll) == -1) {
                    service.buyItemWithChange(userPickItem, userGIvenBIll);
                } else if (service.checkingUserBill(userPickItem).compareTo(userGIvenBIll) == 1) {

                    service.buyItemWithLessInput(userPickItem, userGIvenBIll);

                }
            } while (!service.userItemInput(userPickItem));
        } catch (ItemsDataValidationException
                | DataPersistenceException | UnderPaidPriceException | NoSuchItemInInventoryException ex) {
            System.out.println(" improper item data");
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
