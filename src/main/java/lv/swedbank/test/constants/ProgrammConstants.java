/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.constants;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author aks513
 */
public class ProgrammConstants {
    private ProgrammConstants(){}
    
    public static String HEADER_PANEL_NAME = "Data manipulation";
    public static String CENTRAL_PANEL_NAME = "Information";
    public static String MAIN_APPLICATION_NAME = "Test work";
    public static String CURRENT_DIRECTORY_PATH = ".";
    public static String TEXT_FILE_DELIMETR = "\\|";
    public static String INCORRECT_FILE_EXCEPTION = "Incorrect Data!";
    public static String COMMA = ",";
    public static String FLOAT_VALUE_FORMAT = "%.3f";
    public static String EMPTY_STRING = "";
    public static String MONTH_TEXT = "Month";
    public static String PRICE_TEXT = "Total ammount";
    
    public static int ONE = 1;
    public static int THREE = 3;
    public static int ZERO = 0;
    public static int TWO = 2;
    public static int GRID_SPACE_WITH = 15;
    public static int GRID_SPACE_HEIGHT = 50;
    public static int ARRAY_MAX_LENGTH = 15;
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
