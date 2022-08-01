/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import com.javaonly.flooringmastery.dto.Products;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Prathima
 */
public class ProductsDaoFileImplTest {
    ProductsDao productsTestDao;
    
    public ProductsDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp()throws Exception {
        String testFile = "testProducts.txt";
        new FileWriter(testFile);
        productsTestDao = new ProductsDaoFileImpl(testFile);
    }
    
//    @AfterEach
//    public void tearDown() { //not using
//    }

    @Test
    public void testAddGetMethod() throws DataPersistenceException{
        //craete product
        String productType = " marble";
        Products product = new Products();
        product.setProductType(productType);
        BigDecimal costPerSqFoot = new BigDecimal("3.48");
         BigDecimal laborCost = new BigDecimal("11.78");
        product.setCostPerSquareFoot(costPerSqFoot);
        product.setLaborCostPerSquareFoot(laborCost);
        
        //add the product to dao
        //productsTestDao.addProduct(productType, product);
        //get product
        Products retrieveProduct = productsTestDao.getProduct(productType);
        //check product equal
       // assertEquals(product.getProductType(),retrieveProduct.getProductType(),"Checking if product type same");
        
        
    }
     @Test
    public void testAddAndGetProductsMethod() throws DataPersistenceException{
        //craete product
        
        Products product = new Products();
        String productType = " marble";
        BigDecimal costPerSqFoot = new BigDecimal("3.48");
         BigDecimal laborCost = new BigDecimal("11.78");
         product.setProductType(productType);
        product.setCostPerSquareFoot(costPerSqFoot);
        product.setLaborCostPerSquareFoot(laborCost);
        
        //second product
        
        Products product2 = new Products();
        String productType2 = " tile ";
        product2.setProductType(productType2);
        BigDecimal costPerSqFoot2 = new BigDecimal("5.48");
         BigDecimal laborCost2 = new BigDecimal("16.78");
        product2.setCostPerSquareFoot(costPerSqFoot2);
        product2.setLaborCostPerSquareFoot(laborCost2);
        
        
        //add the product to dao
        //productsTestDao.addProduct(product.getProductType(), product);
       // productsTestDao.addProduct(product2.getProductType(), product2);
        //now check the list of products
        //try{
        List<Products> allProducts = productsTestDao.getAllProducts();
       // allProducts = productsTestDao.getAllProducts();
        //check contents by using asserts
       // assertNotNull(allProducts,"THe list of Products must not null");
        //assertEquals(2,allProducts.size(),"List of products should be 2");
//        }catch(NoSuchProductException e){
//            System.out.println("no such product exists on ur test file");
//        }
        
    }
    
}
