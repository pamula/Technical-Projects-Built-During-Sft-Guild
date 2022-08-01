/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Products;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prathima
 */
public class ProductsDaoStubImpl implements ProductsDao{
    public Products onlyProduct;
    
    public ProductsDaoStubImpl(){
        onlyProduct = new Products();
        onlyProduct.setProductType("marble");
        BigDecimal costPerSqFoot = new BigDecimal("3.45");
        onlyProduct.setCostPerSquareFoot(costPerSqFoot);
        BigDecimal laborCost = new BigDecimal("11.45");
        onlyProduct.setLaborCostPerSquareFoot(laborCost);
    }
    public ProductsDaoStubImpl(Products testProduct){
        this.onlyProduct = testProduct;
    }

//    @Override
//    public Products addProduct(String productType, Products productInfo) throws DataPersistenceException {
//       if(productType.equalsIgnoreCase(onlyProduct.getProductType())){
//           return onlyProduct;
//       }else{
//           return null;
//       }
//    }

    @Override
    public List<Products> getAllProducts() throws DataPersistenceException {
       List<Products> productList = new ArrayList<>();
       productList.add(onlyProduct);
       return productList;
    }

    @Override
    public Products getProduct(String productType) throws DataPersistenceException {
         if(productType.equalsIgnoreCase(onlyProduct.getProductType())){
             return onlyProduct;
         }else{
             return null;
         }
    }
    
}
