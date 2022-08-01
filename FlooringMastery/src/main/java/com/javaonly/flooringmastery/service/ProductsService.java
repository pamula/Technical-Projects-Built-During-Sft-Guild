/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
//import com.javaonly.flooringmastery.dao.NoSuchProductException;
import com.javaonly.flooringmastery.dto.Products;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface ProductsService {
    
   List<Products> getAllProducts()throws DataPersistenceException;
   
   Products getProduct(String productType) throws DataPersistenceException;
}
