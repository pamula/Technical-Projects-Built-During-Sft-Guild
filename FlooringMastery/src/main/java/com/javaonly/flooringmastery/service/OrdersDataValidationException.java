/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

/**
 *
 * @author Prathima
 */
public class OrdersDataValidationException extends Exception{
    public OrdersDataValidationException(String message){
        super(message);
    }
    public OrdersDataValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
