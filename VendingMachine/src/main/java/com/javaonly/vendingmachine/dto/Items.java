/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Prathima
 */
public class Items {
    //private String itemId;
    private String itemName;
    private int quantity;
     private BigDecimal price;

    

   

   public Items(String itemName){
       this.itemName = itemName;
   }
    
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
//    public void setItemId(String itemId){
//        this.itemId = itemId;
//    }
//public String getItemId() {
//        return itemId;
//    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
   
}
