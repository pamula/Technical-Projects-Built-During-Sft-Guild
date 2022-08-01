/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dao;

/**
 *
 * @author Prathima
 */
public class NoSuchItemInInventoryException extends Exception {
     public NoSuchItemInInventoryException(String message) {
        super(message);
    }
    
    public NoSuchItemInInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
