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
public class ProductsDataValidationException extends Exception{
    public ProductsDataValidationException(String message){
        super(message);
    }
    public ProductsDataValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
