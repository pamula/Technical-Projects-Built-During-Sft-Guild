/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.guessthenumberspringbootrestapplication.service;

/**
 *
 * @author Prathima
 */
public class RoundsValidationException extends Exception {
    public RoundsValidationException(String message){
        super(message);
    }
    public RoundsValidationException(String message, Throwable cause){
        super(message, cause);
    }
}
