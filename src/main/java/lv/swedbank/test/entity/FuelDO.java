/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.entity;

import java.time.LocalDate;
import java.time.Month;

import javafx.beans.property.SimpleStringProperty;

import lv.swedbank.test.constants.ProgrammConstants;

/**
 *
 * @author aks513
 */
public class FuelDO {
    private final SimpleStringProperty fuelName;
    private final SimpleStringProperty fuelPrice;
    private final SimpleStringProperty fuelAmount;
    private final SimpleStringProperty refuellingDate;

    public FuelDO(String fuelName, String fuelPrice, String fuelAmount, String refuellingDate) {
        this.fuelName = new SimpleStringProperty(fuelName);
        this.fuelPrice = new SimpleStringProperty(fuelPrice);
        this.fuelAmount = new SimpleStringProperty(fuelAmount);
        this.refuellingDate = new SimpleStringProperty(refuellingDate);
    }

    public String getFuelName() {
        return fuelName.get();
    }

    public void setFuelName(String fuelName) {
        this.fuelName.set(fuelName);
    }

    public double getFuelPrice() {
        return Double.parseDouble(fuelPrice.get());
    }

    public void setFuelPrice(String fuelPrice) {
        this.fuelPrice.set(fuelPrice);
    }

    public double getFuelAmount() {
        return Double.parseDouble(fuelAmount.get());
    }

    public void setFuelAmount(String fuelAmount) {
        this.fuelAmount.set(fuelAmount);
    }

    public String getRefuellingDate() {             
        return refuellingDate.get();
    }

    public void setRefuellingDate(String refuellingDate) {
        this.refuellingDate.set(refuellingDate);
    }
    
    public Month getDateValue() {
        return LocalDate.parse(getRefuellingDate(),ProgrammConstants.DATE_FORMATTER).getMonth();
    }
    
    public double getTotalPrice() {
        return getFuelAmount() * getFuelPrice();
   }
}
