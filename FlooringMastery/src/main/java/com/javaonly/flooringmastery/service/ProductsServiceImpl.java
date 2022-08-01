/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
//import com.javaonly.flooringmastery.dao.NoSuchProductException;
import com.javaonly.flooringmastery.dao.ProductsAuditDao;
import com.javaonly.flooringmastery.dao.ProductsDao;
import com.javaonly.flooringmastery.dto.Products;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class ProductsServiceImpl implements ProductsService{
    
     private ProductsDao dao;
     private ProductsAuditDao auditDao;
     
    public ProductsServiceImpl(ProductsDao dao, ProductsAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Products> getAllProducts() throws DataPersistenceException{
      
       return dao.getAllProducts();
       
    }
    private void validateProductData(Products product) throws ProductsDataValidationException {
        if (product !=null || product.getProductType() == null || product.getProductType().trim().length() == 0
                || product.getCostPerSquareFoot() == null || product.getLaborCostPerSquareFoot() == null) {
            throw new ProductsDataValidationException("ERROR ALL FIELDS [ID,ITEMNAME,BIGDECIMAL VALUE,PRICE] REQUIRED");
        }
    }
    
    
    @Override
    public Products getProduct(String productType) throws DataPersistenceException{
        return dao.getProduct(productType);
    }
     
}
    

