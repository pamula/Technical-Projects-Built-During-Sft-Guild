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
public class ProductsDuplicateIdException extends Exception {
    //here goes 2 constructors///
    public ProductsDuplicateIdException(String message){
        super(message);
    }
    public ProductsDuplicateIdException(String message, Throwable cause){
        super(message, cause);
    }
}
