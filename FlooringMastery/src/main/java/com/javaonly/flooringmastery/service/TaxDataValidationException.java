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
public class TaxDataValidationException extends Exception{
    public TaxDataValidationException(String message){
        super(message);
    }
    public TaxDataValidationException(String message, Throwable cause){
        super(message, cause);
    }
    
}
