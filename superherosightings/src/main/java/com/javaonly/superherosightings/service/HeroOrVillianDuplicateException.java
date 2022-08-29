/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.superherosightings.service;

/**
 *
 * @author Prathima
 */
public class HeroOrVillianDuplicateException extends Exception {
    public HeroOrVillianDuplicateException(String message){
        super(message);
    }
    public HeroOrVillianDuplicateException(String message, Throwable cause){
        super(message, cause);
    }
}
