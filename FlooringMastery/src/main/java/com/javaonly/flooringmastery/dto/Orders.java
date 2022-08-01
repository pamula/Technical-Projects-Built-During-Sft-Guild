/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Prathima
 */
public class Orders {
    /*
    OrderNumber – Integer
CustomerName – String
State – String
    StateAbbreviation -String
TaxRate of state– BigDecimal
ProductType – String
    CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
Area – BigDecimal

MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal
    MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
totalTaxCost = (MaterialCost + LaborCost) * (TaxRateofstate/100)
Tax rates are stored as whole numbers
TotalCost = (MaterialCost + LaborCost + Tax)

    */
   
   // private String date;
 private int orderNumber;
    private String customerName;
    private String state;
     private String productType;
    private BigDecimal taxValues; //taxrate in that state
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
   private  BigDecimal Area;
   private BigDecimal materialCost;
   private BigDecimal totalLaborCost;
   private BigDecimal totalTax;
   private BigDecimal totalOrderCost;
   
//   public Orders(){
//       
//   }
   
//    public Orders(int orderNumber){
//       
//       this.orderNumber = orderNumber;
//       
//   }
    
    @Override   
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.orderNumber;
        hash = 37 * hash + Objects.hashCode(this.customerName);
        hash = 37 * hash + Objects.hashCode(this.state);
        hash = 37 * hash + Objects.hashCode(this.productType);
        hash = 37 * hash + Objects.hashCode(this.taxValues);
        hash = 37 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.Area);
        hash = 37 * hash + Objects.hashCode(this.materialCost);
        hash = 37 * hash + Objects.hashCode(this.totalLaborCost);
        hash = 37 * hash + Objects.hashCode(this.totalTax);
        hash = 37 * hash + Objects.hashCode(this.totalOrderCost);
        return hash;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderNumber=" + orderNumber + ", customerName=" + customerName + ", state=" + state + ", productType=" + productType + ", taxValues=" + taxValues + ", costPerSquareFoot=" + costPerSquareFoot + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + ", Area=" + Area + ", materialCost=" + materialCost + ", totalLaborCost=" + totalLaborCost + ", totalTax=" + totalTax + ", totalOrderCost=" + totalOrderCost + '}';
    }

//", date=" + date this comes after ordernumber +
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orders other = (Orders) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxValues, other.taxValues)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.Area, other.Area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.totalLaborCost, other.totalLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalOrderCost, other.totalOrderCost)) {
            return false;
        }
        return true;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
   
    public String getState() {
        return state;
    }

    public String getProductType() {
        return productType;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTaxValues() {
        return taxValues;
    }

    public void setTaxValues(BigDecimal taxValues) {
        this.taxValues = taxValues;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot){
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setProductValues(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal Area) {
        this.Area = Area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setTotalOrderCost(BigDecimal totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }
   
    
    
    
}
