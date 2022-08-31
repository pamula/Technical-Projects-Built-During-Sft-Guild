/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.ui;

import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class FlooringMasteryView {

    private UserIo io;

    public FlooringMasteryView(UserIo io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {

        io.print("<<Flooring Program>> ");
        io.print(" * 1.Display Tax ");
        io.print(" * 2.Display Products  ");
        io.print(" * 3.Add Order ");
        io.print(" * 4.Edit An Order For Given Date and Order ");
        io.print(" * 5.Display Orders From Given Date");
        io.print(" * 6.remove order");
        io.print(" * 7.Quit ");

        return io.readInt("please select from the above choices", 1, 7);
    }

    

    //to display tax list
    public void displayTaxList(List<Tax> taxList) {
        for (Tax currentTax : taxList) {

            String taxInfo = String.format("#%s : %s %s ", currentTax.getStateAbbrevation(), currentTax.getStateName(),
                    currentTax.getTaxRate());

            io.print(taxInfo);
        }
    }

    //to display product list

    public void displayProductsList(List<Products> productList) {
        for (Products currentProduct : productList) {

            String productInfo = String.format("#%s : %s %s ", currentProduct.getProductType(), currentProduct.getCostPerSquareFoot(),
                    currentProduct.getLaborCostPerSquareFoot());

            io.print(productInfo);
        }
    }
    
    /////////******************ORDERS FROM HERE*******************////////////////////
    //Will Have Products/Taxes Collection Parameters
    /*
    OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
    LaborCostPerSquareFoot,MaterialCost, LaborCost,Tax,Total
     */
    //to take order
    public Orders getNewOrderCheckingFromProducts(List<Products> allProducts, List<Tax> allTaxes) {

       
       // int orderNumber = io.readIntForUserInput("please enter order number");
        //Orders currentOrder = new Orders(orderNumber);
        Orders currentOrder = new Orders();
       //currentOrder.setOrderNumber(1);
        String customerName = io.addOrderCustomerName("please enter name");
        currentOrder.setCustomerName(customerName);
        
        String state = io.readStringForValidateAddOrder("please enter state");
        io.print("choose from product list");
        for (Products productList : allProducts) {
            System.out.println(" product: " + productList.getProductType() + " costsqfoot: " + productList.getCostPerSquareFoot()
                    + " laborcost:  " + productList.getLaborCostPerSquareFoot());
        }
        String productType = io.readStringForValidateAddOrder("please enter product type to place order");
     
      String area = io.checkBigDecimal();
        currentOrder.setArea(new BigDecimal(area));
         
         currentOrder.setCostPerSquareFoot(BigDecimal.ZERO);
         currentOrder.setLaborCostPerSquareFoot(BigDecimal.ZERO);
         currentOrder.setTaxValues(BigDecimal.ZERO);
        BigDecimal value = new BigDecimal("100");
         
        for (Products allProductsList : allProducts) {
            if (allProductsList.getProductType().equalsIgnoreCase(productType)) {
                currentOrder.setProductType(productType);
                  
                currentOrder.setCostPerSquareFoot(allProductsList.getCostPerSquareFoot());
                currentOrder.setLaborCostPerSquareFoot(allProductsList.getLaborCostPerSquareFoot());
            }
        }
         for (Tax allTaxesList : allTaxes) {
            if (allTaxesList.getStateAbbrevation().equalsIgnoreCase(state)) {
                currentOrder.setState(state);
                currentOrder.setTaxValues(allTaxesList.getTaxRate());
            }
        }

        /* ALL CALS
     MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
Tax = (MaterialCost + LaborCost) * (TaxRate/100)
Tax rates are stored as whole numbers
Total = (MaterialCost + LaborCost + Tax)
         */
        

        currentOrder.setMaterialCost((currentOrder.getArea()).multiply(currentOrder.getCostPerSquareFoot())); // setproductfile to currentorder changed

        currentOrder.setTotalLaborCost((currentOrder.getArea()).multiply(currentOrder.getLaborCostPerSquareFoot()));
        BigDecimal  tax1 = BigDecimal.ZERO;
              tax1 =  currentOrder.getMaterialCost().add(currentOrder.getTotalLaborCost());

        BigDecimal tax2 = tax1.multiply(currentOrder.getTaxValues());

        BigDecimal tax3 = tax2.divide(value, 1, RoundingMode.HALF_UP);
        currentOrder.setTotalTax(tax3);
        currentOrder.setTotalOrderCost(currentOrder.getMaterialCost().add(currentOrder.getTotalLaborCost()).add(currentOrder.getTotalTax()));
io.print("YOUR ORDER DETAILS ARE : ");
//io.print("ORDER NUMBER HERE IS : " + currentOrder.getOrderNumber());
io.print("CUSTOMER NAME : " + currentOrder.getCustomerName());
io.print("STATE : " + currentOrder.getState());
io.print("PRODUCT TYPE : " + currentOrder.getProductType());
io.print("AREA : " + currentOrder.getArea());
io.print("TAX VALUE IN THE STATE : " + currentOrder.getTaxValues());
io.print(" COST PER SQUARE FOOT : " + currentOrder.getCostPerSquareFoot());
io.print("LABOR COST PER SQUARE FOOT: " + currentOrder.getLaborCostPerSquareFoot());
io.print("MATERIAL COST : " + currentOrder.getMaterialCost());
io.print("TOTAL LABOR COST : " + currentOrder.getTotalLaborCost());
io.print("TOTAL TAX : " + currentOrder.getTotalTax());
io.print("TOTAL ORDER COST : " + currentOrder.getTotalOrderCost());


        return currentOrder;
    }

    public void displayCreateSuccessBannerOrders() {
        io.readString("Order successfully created please hit enter to continue");
    }

    public void displayPlaceAnOrderOrTax() {
        io.print("===== Place An Order =======");
    }
     public void displayOrderNotPlaced() {
        io.print("===Your Order Not Placed====");
    }

    public void displayOrderNotPlacedStateNotMatch() {
        io.print("===WE DONT TAKE ORDERS FOR GIVEN STATE,ORDER NOT PLACED ====");
    }
    
    
    ///////////************EDIT ORDERS FROM HERE****************///////////////////////
//to edit order
    
    public Orders getNewEditedOrdersInfo(List<Products> allProducts, List<Tax> allTaxes, Orders order) {
        //CustomerName,state,productType,area
        //Enter customer name (Ada Lovelace):

       
       Orders currentOrder = new Orders();
currentOrder.setOrderNumber(order.getOrderNumber());
        //currentOrder.setDate(date);
        String customerName = io.readString("please enter name to change : " + order.getCustomerName());
        if ((customerName.isEmpty())) {
            currentOrder.setCustomerName(order.getCustomerName());
        } else if ((!order.getCustomerName().equalsIgnoreCase(customerName))) {
            currentOrder.setCustomerName(customerName);
        }
        io.print("HERE IS NEW CUSTOMERNAME " + currentOrder.getCustomerName());

        String state = io.readString("please enter state to change : " + order.getState());
        if ((state.isEmpty())) {
            currentOrder.setState(order.getState());
        } else if (!order.getState().equalsIgnoreCase(state)) {
            currentOrder.setState(state);
       
        }
        io.print("HERE IS NEW STSTE " + currentOrder.getState());
        io.print("choose from product list");
        for (Products productList : allProducts) {
            System.out.println(" PRODUCT : " + productList.getProductType() + " COSTPERSQRFOOT : " + productList.getCostPerSquareFoot()
                    + " LABORCOST :  " + productList.getLaborCostPerSquareFoot());
        }

        String productType = io.readString("please enter product type to place order : " + order.getProductType());

        if ((productType.isEmpty())) {
            currentOrder.setProductType(order.getProductType());
        } else if (((!order.getProductType().equalsIgnoreCase(productType)))) {
            currentOrder.setProductType(productType);
        }
        io.print("HERE IS NEW product " + currentOrder.getProductType());
        
         try {
           String area = io.bigDecimalValueForEdit("please enter area in bigdecimal, must be >100 sqft to place order :  " + order.getArea());
        BigDecimal value = new BigDecimal("100");
            if ((area.isEmpty())||(Integer.parseInt(area)<100)) {
                currentOrder.setArea(order.getArea());

            } 
            else if (((order.getArea().compareTo(new BigDecimal(area)) == -1)) || ((order.getArea().compareTo(new BigDecimal(area)) == 1)) || ((order.getArea().compareTo(new BigDecimal(area)) == 0))) {
                currentOrder.setArea(new BigDecimal(area));
            }
        } catch (NumberFormatException | NullPointerException e) {
           System.out.println("number format error here check the input " + e);
        }

        BigDecimal value = new BigDecimal("100");

         currentOrder.setCostPerSquareFoot(BigDecimal.ZERO);
         currentOrder.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        for (Products allProductsList : allProducts) {
            if (allProductsList.getProductType().equalsIgnoreCase(currentOrder.getProductType())) {
                //currentOrder.setProductType(productType);
                currentOrder.setCostPerSquareFoot(allProductsList.getCostPerSquareFoot());
                currentOrder.setLaborCostPerSquareFoot(allProductsList.getLaborCostPerSquareFoot());

            }
        }
         currentOrder.setTaxValues(BigDecimal.ZERO);

//        }
        for (Tax allTaxesList : allTaxes) {
            if (allTaxesList.getStateAbbrevation().equalsIgnoreCase(currentOrder.getState())) {
                currentOrder.setTaxValues(allTaxesList.getTaxRate());

            }
        }
        io.print("state here is " + currentOrder.getState());
        io.print("HERE IS AREA " + currentOrder.getArea());
        io.print("HERE ITS BIG DECIMAL TAX VALUE " + currentOrder.getTaxValues());

        io.print("HERE ITS  COST PER SQUARE FOOT VALUE " + currentOrder.getCostPerSquareFoot());
        io.print("HERE ITS BIG DECIMAL LABOR COST PER SQUARE FOOT  VALUE " + currentOrder.getLaborCostPerSquareFoot());

        currentOrder.setMaterialCost((currentOrder.getArea()).multiply(currentOrder.getCostPerSquareFoot())); // setproductfile to currentorder changed

        currentOrder.setTotalLaborCost((currentOrder.getArea()).multiply(currentOrder.getLaborCostPerSquareFoot()));

        BigDecimal tax1 = currentOrder.getMaterialCost().add(currentOrder.getTotalLaborCost());
       
        BigDecimal tax3 = tax1.multiply(currentOrder.getTaxValues());
       
        BigDecimal tax2 = tax3.divide(value, 1, RoundingMode.HALF_UP);
       
        currentOrder.setTotalTax(tax2);
        currentOrder.setTotalOrderCost(currentOrder.getMaterialCost().add(currentOrder.getTotalLaborCost()).add(currentOrder.getTotalTax()));

        io.print("MATERIAL  COST : " + currentOrder.getMaterialCost());
        io.print("TOTAL LABOR COST : " + currentOrder.getTotalLaborCost());
        io.print("TOTAL TAX COST : " + currentOrder.getTotalTax());
        io.print("TOTAL ORDER COST : " + currentOrder.getTotalOrderCost());
        return currentOrder;
    }

    public String userChoiceToPlaceOrder() {
        String userChoice = io.readString("do u want to place order? Y/N");
        return userChoice;
    }

    public String userStateInput() {
        String userChoice = io.readString("enter the state abbreviation to place order");
        return userChoice;
    }

   

    public String displayAllOrdersAsString(String ordersWithSameDate) {
        return ordersWithSameDate;
    }

    public void displayEditOrderBanner() {
        io.print("===Edit an Order====");
    }
    /////////******************* REMOVE ORDERS FROM HERE**************** ////////////

    public void displayRemoveOrderBanner() {
        io.print("====Remove ORDER====");
    }
     public void displayNotRemoveOrderBanner() {
        io.print("==== ORDER NOT REMOVED====");
    }
     public void displayRemovedOrderBanner(){
          io.print("==== ORDER  REMOVED====");
     }
    public void displayOrderList(List<Orders> orderList) {
        for (Orders currentOrder : orderList) {
            if(currentOrder !=null){

            String orderInfo = String.format("%s %s %s %s %s %s %s %s %s %s %s %s ",currentOrder.getOrderNumber(),  currentOrder.getCustomerName(), currentOrder.getState(),
                    currentOrder.getTaxValues(), currentOrder.getProductType(), currentOrder.getArea(),
                    currentOrder.getCostPerSquareFoot(), currentOrder.getLaborCostPerSquareFoot(),
                    currentOrder.getMaterialCost(), currentOrder.getTotalLaborCost(), currentOrder.getTotalTax(), currentOrder.getTotalOrderCost());
            io.print(orderInfo);
        }
            else{
                io.print("NO SUCH ORDER EXIST");
                }
        }

    }

    public void displayForOneOrder(List<Orders> allOrders, int num) {
        for (Orders order : allOrders) {
            if (allOrders != null && order.getOrderNumber() == num) {
               
                String orderInfo = String.format("%s %s %s %s %s %s %s %s %s %s %s %s ", order.getOrderNumber(), order.getCustomerName(), order.getState(),
                        order.getTaxValues(), order.getProductType(), order.getArea(),
                        order.getCostPerSquareFoot(), order.getLaborCostPerSquareFoot(),
                        order.getMaterialCost(), order.getTotalLaborCost(), order.getTotalTax(), order.getTotalOrderCost());
                io.print(orderInfo);
            } 
        }
    }

    public void displayOrders(Orders order) {
        if(order!=null){
        io.print(order.toString());
        }else{
            io.print("given order doesnt exist/null");
        }

    }
    
    public int orderNumberTocheck() {
        int userChoice = io.readIntForUserInput("enter order number ");

        return userChoice;
    }
   

    public String dateFromOrderList() {

        String dateToBeChecked = io.orderDate("please give the future date in yyyy-mm-dd format to place the order  ");

        return dateToBeChecked;
    }

    public String dateForEditAndListOrder() {
        String dateToBeChecked = io.dateForEditAndListOrders("please give the date in yyyy-mm-dd format to get an order or list orders");

        return dateToBeChecked;
    }

    public void displayOrderListFromUserDateChoice() {
        io.print("Listing all orders from given date");
    }

    public void displayOrderListFromUserDateOK() {
        io.print("got all orders from given date");
    }
public void displayOrderListFromUserDateNotOK() {
        io.print("NO ORDERS FROM GIVEN DATE");
    }

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
