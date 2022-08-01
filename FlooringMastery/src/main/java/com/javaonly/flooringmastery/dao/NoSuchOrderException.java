/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

/**
 *
 * @author Prathima
 */
public class NoSuchOrderException extends Exception {
     public NoSuchOrderException(String message) {
        super(message);
    }
    
    public NoSuchOrderException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
