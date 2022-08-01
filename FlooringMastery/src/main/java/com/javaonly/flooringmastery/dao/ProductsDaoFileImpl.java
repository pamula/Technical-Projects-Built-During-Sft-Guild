/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import static com.javaonly.flooringmastery.dao.OrdersDaoFileImpl.DELIMETER;
import com.javaonly.flooringmastery.dto.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prathima
 */
public class ProductsDaoFileImpl implements ProductsDao{
     public final String PRODUCT_FILE;
      public static final String DELIMETER = ",";
      
      public ProductsDaoFileImpl() {
       
        PRODUCT_FILE = "Data/product.txt";
        
    }
       public ProductsDaoFileImpl( String productTextFile) {
      
        PRODUCT_FILE = productTextFile;
       
    }
        private Map<String, Products> products = new HashMap<>();
     

    @Override
    public List<Products> getAllProducts() throws DataPersistenceException {
       
             loadProduct();
        return new ArrayList(products.values());
        //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Products getProduct(String productType) throws DataPersistenceException{
        
             loadProduct();
        return products.get(productType);
    }

    private Products unMarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMETER);
        String productIdAsProductType = productTokens[0];
//        Products productFromFile = new Products(productIdAsProductType);
        Products productFromFile = new Products();
        productFromFile.setProductType(productIdAsProductType);

        productFromFile.setCostPerSquareFoot(new BigDecimal(productTokens[1]));
        productFromFile.setLaborCostPerSquareFoot(new BigDecimal(productTokens[2]));
        return productFromFile;
    }

    private void loadProduct() throws DataPersistenceException{
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
 } catch (FileNotFoundException  e) {
           throw new DataPersistenceException(
                "-_- Could not load product data into memory.", e);
        }

            String currentLine;
            Products currentProduct;
            //here this code until else will skip the first line in txt file
            //wont get error while unmarshall the file
            if (scanner.hasNext() == true) {
                scanner.nextLine();
            } else {
                System.out.println("Error: File is empty");

            }
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentProduct = unMarshallProduct(currentLine);
                products.put(currentProduct.getProductType(), currentProduct);
            }
            scanner.close();
       
    }

    //marshall does this // wood,5.67,24.8
    private String marshallProduct(Products aProduct) {
        String productAsText = aProduct.getProductType() + DELIMETER;
        productAsText += aProduct.getCostPerSquareFoot() + DELIMETER;
        productAsText += aProduct.getLaborCostPerSquareFoot();
        return productAsText;
    }

    private void writeProduct() throws DataPersistenceException{
        PrintWriter out;
try {
        out = new PrintWriter(new FileWriter(PRODUCT_FILE));
    } catch (IOException e) {
        throw new DataPersistenceException(
                "Could not save product data.", e);
    }
        String productAsText;
        String header = "ProductType,CostPerSquareFoot,LaborCostPerSquareFoot";
         out.println(header);
        List<Products> productList = this.getAllProducts();
       
        for (Products currentProduct : productList) {
            // turn a product into a String
            productAsText = marshallProduct(currentProduct);
            // write the product object to the file
            out.println(productAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
