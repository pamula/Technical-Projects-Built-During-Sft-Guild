/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.service;

/**
 *
 * @author Prathima
 */
public class UnderPaidPriceException extends Exception{
    
    public UnderPaidPriceException(String message) {
        super(message);
    }
    
    public UnderPaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }
}
