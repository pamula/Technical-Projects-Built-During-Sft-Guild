/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Prathima
 */
public class UserIoConsoleImpl implements UserIo{
     final private Scanner console = new Scanner(System.in);

    ////A very simple method that takes in a message to display on the console 
   
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        return console.nextLine();
    }
    @Override
public String addOrderCustomerName(String msg){
    String userInput = "";
     boolean userChoice = false;
       while( !userChoice  ){  
           //t t, f t
           System.out.println(msg);
           userInput =  console.nextLine();
           if(!userInput.isEmpty()|| userInput.matches("[^A-Za-z0-9,.]")){
               userChoice = true;
           }else{
               userChoice = false;
           }
}
       return userInput;
}
    //this one for add order methods
    
    @Override
    public String readStringForValidateAddOrder(String prompt) {
     
     String userInput = "";
     boolean userChoice = false;
       while( !userChoice  ){  
           //t t, f t
           System.out.println(prompt);
           userInput =  console.nextLine();
           if((!userInput.isEmpty())&& userInput.matches("^[a-zA-Z]+$")){
               userChoice = true;
           }else{
               userChoice = false;
           }
           //userInput = readStringForValidate( prompt);
           //Use Validation Line of Code to Validate userInput - if valid set UserChoice to true
           //If False let user they did something bad and don't change userChoice and it will loop
         
      //userChoice = false;
       }
      // console.nextLine();
        return userInput;

    }
   
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                
                String stringValue = this.readString(msgPrompt);
               
                num = Integer.parseInt(stringValue);
               
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        //console.nextLine();
        return num;
    }
    @Override
    public int readIntForUserInput(String msg){
         boolean invalidInput = true;
          int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt 
                String stringValue = this.readString(msg);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue);
               
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }
    
    
    @Override
    public String bigDecimalValueForEdit(String prompt){
 
           String valueCheck = "";
      
             try{
              System.out.println(prompt);   
            
           valueCheck = console.nextLine();
           
            }catch(NumberFormatException e){
              this.print("Input error. Please try again.");  
            }
        
            
        return valueCheck;
    }
//    
    //this one for add order method so that no empty spaces accepted
   
    

 @Override
    public String readValidBigDecimalForInput(String msg){
     System.out.println(msg);

       String userInput=console.nextLine();
       
       while(userInput.isEmpty()){
          System.out.println(msg);
          userInput=console.nextLine();
       }
    return userInput;
    }
    @Override
    public String checkBigDecimal(){
         BigDecimal value = new BigDecimal(0);
            BigDecimal valueToCompare = new BigDecimal("100");
           
        do{
             try{
            String valueCheck = this.readValidBigDecimalForInput("please enter valid bigdecimal > 100");
            value = new BigDecimal(valueCheck);
            }catch(NumberFormatException e){
              this.print("Input error. Please try again.");  
            }
        }while(value.compareTo(valueToCompare)==-1);
            
        return value.toString();
    }
    
@Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }
    


    //this date only to take orders, i.e date has to be in future only
    @Override
   public String orderDate(String msgPrompt) {
       
        System.out.println(msgPrompt);
        Scanner console = new Scanner(System.in);
       
      String userInput =  "HI";//2021-12-16
    // String userInputToTest = userInput;
      boolean userChoice = true;
        while(userChoice){
         try{
         LocalDate ld1 = LocalDate.now(); //yyyy-mm-dd 2021-12-16
        
         LocalDate totestdate = LocalDate.now();
         
         String beforeFormateTodayDate = ld1.toString();
         ld1 = LocalDate.parse(beforeFormateTodayDate);
          String isoDate = ld1.toString();
          //System.out.println(ld1);
          ld1= LocalDate.parse(isoDate);
        
         userInput =  console.nextLine(); 
          userInput = userInput.toString();
        LocalDate ld3 = LocalDate.parse(userInput);
        String isoDate1 = ld3.toString();
         // System.out.println(ld3 + "ld3, user date here");
          ld3= LocalDate.parse(isoDate1);

          String formatted2 = ld3.format(DateTimeFormatter.ofPattern("MMddyyyy"));
         
         while(ld3.isBefore(totestdate)){
            System.out.println(msgPrompt);
      userInput =  console.nextLine();
      ld3 = LocalDate.parse(userInput);
       isoDate1 = ld3.toString();
          //System.out.println(ld3 + "ld3 here");
          ld3= LocalDate.parse(isoDate1);
          
          formatted2 = ld3.format(DateTimeFormatter.ofPattern("MMddyyyy"));
         
       }
          userInput = formatted2; 
           userChoice = false;
          }
       catch(DateTimeParseException e){
               System.out.println("give the correct date format");
               }
        }
        return userInput; 
}
   //this is only for edit and list orders ,since we need to check all dates
  @Override
  public String dateForEditAndListOrders(String msgPrompt){
       System.out.println(msgPrompt);
        Scanner console = new Scanner(System.in);
        boolean userChoice = true;
        String userInput =  "HI";//2021-12-16
        while(userChoice){
        try{
             userInput = console.nextLine();//2021-12-26
            //userInput = userInput.toString();
        LocalDate ld3 = LocalDate.parse(userInput); //today's date
        String isoDate1 = ld3.toString();
          //System.out.println(ld3 + "ld3, user date here");
          ld3= LocalDate.parse(isoDate1);

          String formatted2 = ld3.format(DateTimeFormatter.ofPattern("MMddyyyy"));
          userInput = formatted2;
          // System.out.println(userInput + "aftere formatted");
            userChoice = false;
        }catch(DateTimeParseException e){
           System.out.println("GIVE CORRECT DATE/FORMAT AS SHOWN");
        }
        }
       return userInput;
  }
   
}
