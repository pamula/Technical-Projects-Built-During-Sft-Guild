/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Orders;
import com.javaonly.flooringmastery.dto.Products;
import com.javaonly.flooringmastery.dto.Tax;
import com.javaonly.flooringmastery.service.DuplicateOrderNumberException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Prathima
 */
public class OrdersDaoFileImpl implements OrdersDao {

    public String ORDER_FILE;
    public static final String DELIMETER = ",";

    public OrdersDaoFileImpl() {
        ORDER_FILE = "Orders/Orders_";
    }

    public OrdersDaoFileImpl(String orderTextFile) {

        ORDER_FILE = orderTextFile;
    }
    private Map<Integer, Orders> orders = new HashMap<>();
   
 
    
   int numbers =0;
    
    //////////////////////////////////////////
    //*******ORDERS FROM HERE //////////////////////////
    @Override
public Orders addOrder(String date, Orders orderInfo) throws DataPersistenceException,
        NoSuchOrderException  {
   
    int orderNumber = 0;
   //Path path = Paths.get(ORDER_FILE +date+".txt");
    try{
       loadOrder(date); 
      
       //Assign Order Number Based on orders data
      // orderNumber = (int) Files.lines(path).count();
      //orderNumber = orderNumber+1;
      
       Set<Integer> keys = orders.keySet();
       orderNumber = keys.size();
       orderNumber = orderNumber+1;
      
    }catch(DataPersistenceException ex){
        //File Doesn't Exist, Order Number is 1
        orderNumber = 1;
    }
        orderInfo.setOrderNumber(orderNumber);
        
      Orders newOrder = orders.put(orderInfo.getOrderNumber(), orderInfo);
      
            writeOrder(date);
           
   
        return newOrder;

    }

    @Override

    public boolean editOrder(String date, int orderNumber, Orders order) throws DataPersistenceException,NoSuchOrderException{
       
                loadOrder(date);
            
            Orders newOrder = orders.put(orderNumber, order);
                writeOrder(date);
            return true;
//        
    }

    @Override
    public List<Orders> getAllOrders(String date) throws DataPersistenceException 
            {
               
                loadOrder(date);
            return new ArrayList(orders.values());
//        
    }

    @Override
    public Orders removeOrder(String date, int numberAsString) throws DataPersistenceException
             {
                
                loadOrder(date);
                
          Orders  removedOrders = orders.remove(numberAsString);;
           
            writeOrder(date);
            
            return removedOrders;

    }

    @Override
    public Orders getOneOrder(String date, int orderNum) throws DataPersistenceException, NoSuchOrderException  {

        Orders selectedOrder = null;
            
                loadOrder(date);
                
                Iterator<Map.Entry<Integer, Orders>> it = orders.entrySet().iterator();
                while (it.hasNext()) {
                   
                    Map.Entry<Integer, Orders> entry = it.next();

                    if ((entry.getValue().getOrderNumber() == orderNum)) {

                        selectedOrder = entry.getValue();

                    }

                }
            return selectedOrder;
//       
    }

    /*
        OrderNumber – Integer
CustomerName – String
State – String
TaxRate – BigDecimal
ProductType – String
Area – BigDecimal
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal
    OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
    LaborCostPerSquareFoot,MaterialCost, LaborCost,Tax,Total
     */
    private Orders unMarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMETER);
       // String orderIdAsOrderNum = orderTokens[0];//0 orderNumber
        //Orders orderFromFile = new Orders(Integer.parseInt(orderIdAsOrderNum));
Orders orderFromFile = new Orders();
orderFromFile.setOrderNumber(Integer.parseInt(orderTokens[0]));

orderFromFile.setCustomerName((orderTokens[1]));
boolean onlyComas = (orderFromFile.getCustomerName().matches("^,+$"));
if(onlyComas){
    orderFromFile.setCustomerName((orderTokens[1]).replace(DELIMETER, ""));
    String nowCustomer = orderFromFile.getCustomerName();
    orderFromFile.setCustomerName(nowCustomer);
    
}else{
   orderFromFile.setCustomerName((orderTokens[1])); 
}

        //orderFromFile.setCustomerName((orderTokens[1]).replace(DELIMETER, ""));
        orderFromFile.setState(orderTokens[2]);
       orderFromFile.setTaxValues((new BigDecimal(orderTokens[3])));
        
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setProductValues(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7])); //7
        // orderFromFile.setProductValues(productFromFile);//6
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setTotalLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTotalTax(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotalOrderCost(new BigDecimal(orderTokens[11]));

        return orderFromFile;
    }

    //Parameter Representing Date
    private void loadOrder(String date) throws DataPersistenceException {

        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE +date+".txt")));
            } catch (FileNotFoundException e) {
     throw new DataPersistenceException(
                "-_- Could not load orders .", e);
    }
 
        String currentLine;
        Orders currentOrder;
            //here this code until else will skip the first line in txt file
            //wont get error while unmarshall the file
//           

if (scanner.hasNext()) {
                scanner.nextLine();
            }
 //try{
            while (scanner.hasNextLine()) {
               
                currentLine = scanner.nextLine();
              
                   currentOrder = unMarshallOrder(currentLine);
                
           
                orders.put((currentOrder.getOrderNumber()), currentOrder);
            }
// }catch(NumberFormatException e){
//         
//         }
////            
            scanner.close();
       

    }

    //marshall does this // 1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06
    /*
        OrderNumber – Integer
CustomerName – String
State – String
TaxRate – BigDecimal
ProductType – String
Area – BigDecimal
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal
     */
    private String marshallOrder(Orders anOrder) {
        
        String orderAsText = anOrder.getOrderNumber() + DELIMETER;
        orderAsText += anOrder.getCustomerName() + DELIMETER;
        orderAsText += anOrder.getState() + DELIMETER;

        orderAsText += anOrder.getTaxValues() + DELIMETER;
        orderAsText += anOrder.getProductType() + DELIMETER;
        orderAsText += anOrder.getArea() + DELIMETER;
        orderAsText += anOrder.getCostPerSquareFoot() + DELIMETER;
        orderAsText += anOrder.getLaborCostPerSquareFoot() + DELIMETER;

        orderAsText += anOrder.getMaterialCost() + DELIMETER;
        orderAsText += anOrder.getTotalLaborCost() + DELIMETER;
        orderAsText += anOrder.getTotalTax() + DELIMETER;
        orderAsText += anOrder.getTotalOrderCost();

        return orderAsText;
    }

    private void writeOrder(String date) throws DataPersistenceException {

        PrintWriter out;
        

        try {

            out = new PrintWriter(new FileWriter(ORDER_FILE+date+".txt"));

        } catch (IOException e) {
            throw new DataPersistenceException(
                    "Could not save orders data.", e);
        }
        //}
//        

        String orderAsText;
        String header = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,"
                + "LaborCostPerSquareFoot,MaterialCost,"
                + " LaborCost,Tax,Total";
        List<Orders> orderList = this.getAllOrders(date);
        out.println(header);

        for (Orders currentOrder : orderList) {
            
            // turn order into a String
           
            orderAsText = marshallOrder(currentOrder);
            
            // write the order object to the file
            out.println(orderAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        out.close();
    }
   

}
