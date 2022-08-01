/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.ui;

import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author Prathima
 */
public interface UserIo {
   void print(String msg);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readString(String prompt);
    String checkBigDecimal();
    String bigDecimalValueForEdit(String prompt);
    String addOrderCustomerName(String msg);
    
 String orderDate(String userDate);
 String dateForEditAndListOrders(String userDate);
 int readIntForUserInput(String msg);
 String readValidBigDecimalForInput(String prompt);
 
 String readStringForValidateAddOrder(String prompt);
 
}
