/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.controller;

import com.javaonly.flooringmastery.dao.DataPersistenceException;

import com.javaonly.flooringmastery.dao.NoSuchOrderException;

import com.javaonly.flooringmastery.dao.NoSuchStateException;
import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import com.javaonly.flooringmastery.service.DuplicateOrderNumberException;
import com.javaonly.flooringmastery.service.OrdersDataValidationException;
import com.javaonly.flooringmastery.service.OrdersService;
import com.javaonly.flooringmastery.service.ProductsDataValidationException;
import com.javaonly.flooringmastery.service.ProductsService;
import com.javaonly.flooringmastery.service.TaxService;
import com.javaonly.flooringmastery.ui.FlooringMasteryView;
import com.javaonly.flooringmastery.ui.UserIo;
import com.javaonly.flooringmastery.ui.UserIoConsoleImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class OrdersController {

    private FlooringMasteryView view;
    private UserIo io = new UserIoConsoleImpl();
    private OrdersService service;
    private TaxService taxService;
    private ProductsService productsService;

    public OrdersController(FlooringMasteryView view, OrdersService service, ProductsService productsService, TaxService taxService) {
        this.view = view;
        this.service = service;
        this.productsService = productsService;
        this.taxService = taxService;
    }

    public void run()  {
        boolean keepGoing = true;
         while (keepGoing) {
        try {
            int menuSelection = 0;

//            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listTax();
                        break;
                    case 2:
                        listProduct();
                        break;
                    case 3:
                        createOrder();
                        break;
                    case 4:
                        editOrderForGivenDate();
                        break;
                    case 5:
                        listAllOrdersFromGivenDate();
                        break;
                    case 6:
                       
                        removeOrder();
                        break;

                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                 exitMessage();

            }catch (DataPersistenceException  
                 | DuplicateOrderNumberException | OrdersDataValidationException|NoSuchOrderException e) {

            view.displayErrorMessage(e.getMessage());
        }

//            exitMessage();
        } 
//         
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listTax() throws DataPersistenceException {

        List<Tax> taxList = taxService.getAllTax();
        view.displayTaxList(taxList);

    }

    private void listProduct() throws DataPersistenceException {

        List<Products> productList = productsService.getAllProducts();
        view.displayProductsList(productList);

    }

    private void createOrder() throws 
            DataPersistenceException, DuplicateOrderNumberException, OrdersDataValidationException,NoSuchOrderException  {
        //Get Products/Taxes List From Service
        view.displayPlaceAnOrderOrTax();
        String date = view.dateFromOrderList();
        
        Orders currentOrder = view.getNewOrderCheckingFromProducts(productsService.getAllProducts(), taxService.getAllTax());
 
        String userChoice = view.userChoiceToPlaceOrder();

 if (userChoice.equalsIgnoreCase("y") ) {
            service.createOrder(date, currentOrder);
            
            view.displayCreateSuccessBannerOrders();
        } else {
            view.displayOrderNotPlaced();
        }
    }

    private void listAllOrdersFromGivenDate() throws DataPersistenceException  {
        view.displayOrderListFromUserDateChoice();

        String dateFromUser = view.dateForEditAndListOrder();
        try{
            List<Orders> orders = service.getAllOrders(dateFromUser);
            if(orders!=null){
            view.displayOrderList(orders);

            view.displayOrderListFromUserDateOK();
            }else{
                view.displayOrderListFromUserDateNotOK();
            }
        }catch(NoSuchOrderException e){
            System.out.println("no such order exist");
        }
       
    }

    private void editOrderForGivenDate() throws DataPersistenceException, 
            DuplicateOrderNumberException, OrdersDataValidationException,NoSuchOrderException {

        view.displayEditOrderBanner();

        String dateFromUser = view.dateForEditAndListOrder();
        int orderNumberToEdit = view.orderNumberTocheck();
       
        Orders oneOrder = service.getAnOrder(dateFromUser, orderNumberToEdit);
       if (oneOrder != null) {
        Orders newEditedOrder = view.getNewEditedOrdersInfo(productsService.getAllProducts(), taxService.getAllTax(), service.getAnOrder(dateFromUser, orderNumberToEdit));

            view.displayForOneOrder(service.getAllOrders(dateFromUser), orderNumberToEdit);
            String userChoice = view.userChoiceToPlaceOrder();
            if (userChoice.equalsIgnoreCase("y")) {
                service.editOrder(dateFromUser, orderNumberToEdit, newEditedOrder);
                view.displayCreateSuccessBannerOrders();
                 }
             else {
            view.displayOrderNotPlaced();
        }
            
        }
       else {
            view.displayOrderNotPlaced();
        }
        
    }

    private void removeOrder() throws DataPersistenceException,NoSuchOrderException {
        String dateFromUser = view.dateForEditAndListOrder();
        int orderNumber = view.orderNumberTocheck();
         
        Orders orderToCancel = service.getAnOrder(dateFromUser, orderNumber);
        if (orderToCancel != null) {
            view.displayOrders(orderToCancel);
             String userChoice = view.userChoiceToPlaceOrder();

              if(userChoice.equalsIgnoreCase("y")) {
                service.removeOrder(dateFromUser, orderToCancel.getOrderNumber());
                view.displayRemovedOrderBanner();
            }else {
            view.displayNotRemoveOrderBanner();
        }
              view.displayNotRemoveOrderBanner();
        }
       
    
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
