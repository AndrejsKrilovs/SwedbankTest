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
public enum MainButtonNameEnum {
    LOAD_DATA("Load"),
    STATISTICS("Statistics"),
    CLEAR_DATA("Clear data");
    
    private final String value;

    private MainButtonNameEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
