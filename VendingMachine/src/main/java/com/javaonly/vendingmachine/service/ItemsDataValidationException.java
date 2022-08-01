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
public class ItemsDataValidationException extends Exception {
    public ItemsDataValidationException(String message){
        super(message);
    }
    public ItemsDataValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
