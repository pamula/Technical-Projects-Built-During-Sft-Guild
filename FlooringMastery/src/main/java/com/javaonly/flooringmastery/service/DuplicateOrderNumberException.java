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
public class DuplicateOrderNumberException extends Exception{
    public DuplicateOrderNumberException(String message){
        super(message);
    }
    public DuplicateOrderNumberException(String message, Throwable cause){
        super(message, cause);
    }
}
