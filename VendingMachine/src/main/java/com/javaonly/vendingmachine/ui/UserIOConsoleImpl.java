/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Prathima
 */
public class UserIOConsoleImpl implements UserIO {

    final private Scanner console = new Scanner(System.in);

    ////A very simple method that takes in a message to display on the console 
    // * and then waits for a integer answer from the user to return.
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }


    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt 
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue);
                if(num>2 || num<1)
                System.out.println("u have only options 1 or 2 numbers to choose");

                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }
    @Override
    public BigDecimal readValidBigDecimal(String prompt){
        boolean invalidInput = true;
        BigDecimal userInput = new BigDecimal(0);
        while(invalidInput){
            try{
    //System.out.println(prompt);
     String stringValue = this.readString(prompt);
  
   userInput = new BigDecimal(stringValue);
    
    invalidInput = false;
        }catch (NumberFormatException e) {
                // If it explodes, it'll go here error handling msg.
                this.print("Input error. Please try again.");
            }
           
    }
         return userInput;
    }

    @Override
    public String readString(String prompt) {
       
       
            System.out.println(prompt);
        
        
        return console.nextLine();

    }

    
@Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }
    
//    
    @Override
    public BigDecimal readBigDecimal(String msgPrompt) {
        BigDecimal userInput = new BigDecimal(0);
         BigDecimal valueToCompare = new BigDecimal("0");
        do{
            userInput = readValidBigDecimal(msgPrompt);
        } while (userInput.compareTo(valueToCompare) == -1 || userInput.compareTo(valueToCompare) == 0 );
   
    return userInput;
    }
    
}
