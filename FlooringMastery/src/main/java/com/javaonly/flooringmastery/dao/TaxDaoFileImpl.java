/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dao;

import static com.javaonly.flooringmastery.dao.OrdersDaoFileImpl.DELIMETER;
import com.javaonly.flooringmastery.dto.Tax;
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
public class TaxDaoFileImpl implements TaxDao{
     public final String TAX_FILE;
      public static final String DELIMETER = ",";
      
       public TaxDaoFileImpl() {
        TAX_FILE = "Data/tax.txt";
        
    }

    public TaxDaoFileImpl(String taxTextFile) {
        TAX_FILE = taxTextFile;
      
    }

    private Map<String, Tax> taxes = new HashMap<>();
    
    @Override
    public List<Tax> getAllTaxes() throws DataPersistenceException{
        loadTax();
        return new ArrayList(taxes.values());

    }
    
    @Override
    public Tax getTax(String stateAbbreviation)throws DataPersistenceException {
        
             loadTax();
        return taxes.get(stateAbbreviation);
    }

    private Tax unMarshallTax(String taxAsText) {
        String[] taxTokens = taxAsText.split(DELIMETER);
        Tax taxFromFile = new Tax();
        taxFromFile.setStateAbbrevation(taxTokens[0]);
        taxFromFile.setStateName(taxTokens[1]);
        taxFromFile.setTaxRate(new BigDecimal(taxTokens[2]));
        return taxFromFile;
    }

    private void loadTax() throws DataPersistenceException {
        Scanner scanner;
        try {
            //here Data/tax.txt file path already added in constructor
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
} catch (FileNotFoundException e) {
           throw new DataPersistenceException("-_- Could not load roster data into memory.", e);
        }
            String currentLine;
            Tax currentTax;
            //here this code until else will skip the first line in txt file
            //wont get error while unmarshall the file
            if (scanner.hasNext() == true) {
                scanner.nextLine();
            }
//            } else {
//                System.out.println("Error: File is empty");
//
//            }
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTax = unMarshallTax(currentLine);
                taxes.put(currentTax.getStateAbbrevation(), currentTax);
            }
            scanner.close();
        

    }

    //marshall does this // Tx,Texas,3.45
    private String marshallTax(Tax aTax) {
        String taxAsText = aTax.getStateAbbrevation() + DELIMETER;

        taxAsText += aTax.getStateName() + DELIMETER;
        taxAsText += aTax.getTaxRate();
        return taxAsText;
    }

    private void writeTax() throws DataPersistenceException {
        
        PrintWriter out;
                try {
        out = new PrintWriter(new FileWriter(TAX_FILE));
    } catch (IOException e) {
        throw new DataPersistenceException(
                "Could not save student data.", e);
    }


        String taxAsText;
        String header = "State,StateName,TaxRate";
        out.println(header);
        List<Tax> taxList = this.getAllTaxes();

        for (Tax currentTax : taxList) {
            // turn a tax into a String
            taxAsText = marshallTax(currentTax);
            // write the tax object to the file
            out.println(taxAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
