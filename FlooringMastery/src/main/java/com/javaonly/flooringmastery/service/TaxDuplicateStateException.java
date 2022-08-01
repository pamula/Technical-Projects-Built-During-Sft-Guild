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
public class TaxDuplicateStateException extends Exception {
    //here goes 2 constructors///
    public TaxDuplicateStateException(String message){
        super(message);
    }
    public TaxDuplicateStateException(String message, Throwable cause){
        super(message, cause);
    }
}
