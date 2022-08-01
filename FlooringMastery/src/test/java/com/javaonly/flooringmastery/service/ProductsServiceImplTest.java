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
import com.javaonly.flooringmastery.dao.ProductsDaoStubImpl;
import com.javaonly.flooringmastery.dto.Products;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Prathima
 */
public class ProductsServiceImplTest {
    private ProductsService productService;
    
    public ProductsServiceImplTest() {
//        ProductsDao productDao = new ProductsDaoStubImpl();
//        ProductsAuditDao productAuditDao = new ProductsAuditDaoStubImpl();
//        
//        productService = new ProductsServiceImpl(productDao,productAuditDao);
ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    productService = 
        ctx.getBean("productsServiceLayer", ProductsServiceImpl.class);
        
    }
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateDuplicateProductType() {
         //arrange , just create with same product type
       //this was craeted already in daostubimpl so expect exception
       //arrange
       Products product = new Products();
       product.setProductType("marble");
        BigDecimal costPerSqFoot = new BigDecimal("3.48");
         BigDecimal laborCost = new BigDecimal("11.45");
       product.setCostPerSquareFoot(costPerSqFoot);
       product.setLaborCostPerSquareFoot(laborCost);
       //act
//       try{
//           productService.createProduct(product);
//           fail("valid product type duplicate id exception should be thrown.");
//       }catch( NoSuchProductException e){
//          fail("wrong exception thrown." + e);  
//       }catch(ProductsDuplicateIdException e){
//            return;
//       }
    }
    @Test
    public void testInvalidProduct(){
        //arrange
        Products product = new Products();
        product.setProductType("marble");
        BigDecimal costPerSqFoot = new BigDecimal("3.45");
         BigDecimal laborCost = new BigDecimal("11.45");
       product.setCostPerSquareFoot(costPerSqFoot);
       product.setLaborCostPerSquareFoot(laborCost);
//       try{
//           productService.createProduct(product);
//           fail("expect validation exception here");
//       }catch( NoSuchProductException e){
//           fail("incorrect exception thrown" + e);
//       }catch(ProductsDuplicateIdException e){
//       }
        
    }
    //for these two methods down use diff names like testProduct
    @Test
    public void testGetAllProducts() throws DataPersistenceException{
        //arrange, by creating one product
        Products testProduct = new Products();
       testProduct.setProductType("marble");
        BigDecimal costPerSqFoot = new BigDecimal("3.45");
         BigDecimal laborCost = new BigDecimal("11.45");
       testProduct.setCostPerSquareFoot(costPerSqFoot);
       testProduct.setLaborCostPerSquareFoot(laborCost);
       //act, assert
       assertEquals(1,productService.getAllProducts().size(),"should have 1 product");
        assertTrue(productService.getAllProducts().contains(testProduct),"the one product must be marble");
        
    }
    @Test
    public void testGetProduct() throws DataPersistenceException{
         Products testProduct = new Products();
       testProduct.setProductType("marble");
        BigDecimal costPerSqFoot = new BigDecimal("3.45");
         BigDecimal laborCost = new BigDecimal("11.45");
       testProduct.setCostPerSquareFoot(costPerSqFoot);
       testProduct.setLaborCostPerSquareFoot(laborCost);
       
       //act ,assert
       Products shouldBeMarble = new Products();
       shouldBeMarble = (productService.getProduct("marble"));
       assertNotNull(shouldBeMarble, " getting marble should not be null");
       assertEquals(testProduct,shouldBeMarble,"product type must be marble for both product objs");
       Products shouldBeNull = new Products();
       shouldBeNull = (productService.getProduct("chocholate"));
       assertNull(shouldBeNull,"getting chocholate should be null");
       
    }
}
