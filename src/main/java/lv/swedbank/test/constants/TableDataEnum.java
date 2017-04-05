/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.constants;

/**
 *
 * @author aks513
 */
public enum TableDataEnum {
    NAME_PROPERTY("fuelName"),
    PRICE_PROPERTY("fuelPrice"),
    AMMOUNT_PROPERTY("fuelAmount"),
    REFUELL_DATE_PROPERTY("refuellingDate");
    
    private final String value;

    private TableDataEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
