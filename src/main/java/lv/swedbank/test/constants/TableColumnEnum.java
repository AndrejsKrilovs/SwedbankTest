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
public enum TableColumnEnum {
    NAME("Fuel name"),
    PRICE("Fuel price"),
    AMMOUNT("Fuel ammount"),
    REFUELL_DATE("Refuell date");
    
    private final String value;

    private TableColumnEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
