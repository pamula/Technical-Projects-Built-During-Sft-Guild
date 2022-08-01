/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.service;

import com.javaonly.flooringmastery.dao.DataPersistenceException;
import com.javaonly.flooringmastery.dao.NoSuchStateException;
import com.javaonly.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface TaxService {
   
   List<Tax> getAllTax()throws DataPersistenceException ;
  
   Tax getTax(String stateAbbreviation) throws DataPersistenceException;
}
