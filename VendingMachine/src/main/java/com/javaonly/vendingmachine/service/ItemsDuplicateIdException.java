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
public class ItemsDuplicateIdException extends Exception {
    //here goes 2 constructors///
    public ItemsDuplicateIdException(String message){
        super(message);
    }
    public ItemsDuplicateIdException(String message, Throwable cause){
        super(message, cause);
    }
    
}
