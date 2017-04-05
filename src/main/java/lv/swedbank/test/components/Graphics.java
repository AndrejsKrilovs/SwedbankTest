/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.components;

import java.time.Month;

import java.time.format.TextStyle;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

import javafx.stage.Modality;
import javafx.stage.Stage;

import lv.swedbank.test.constants.MainButtonNameEnum;
import lv.swedbank.test.constants.ProgrammConstants;

/**
 *
 * @author aks513
 */
public class Graphics {
    private Graphics(){}
    
    private static XYChart.Series<String, Number> setSeries(final Map<String, Map<Month, Double>> collection, final String fuelName) {
        final XYChart.Series<String, Number> fuelStatistics = new XYChart.Series<>();
        fuelStatistics.setName(fuelName);
        
        Stream.of(Month.values()).forEach(month -> {            
            fuelStatistics.getData().add(new XYChart.Data<>(
                month.getDisplayName(TextStyle.SHORT, Locale.US),
                collection.get(fuelName).getOrDefault(month, 0d)
            ));
            
            final Tooltip tooltip = new Tooltip(String.format(
                ProgrammConstants.FLOAT_VALUE_FORMAT, collection.get(fuelName).getOrDefault(month, 0d)
            ));
            
            Tooltip.install(fuelStatistics.nodeProperty().get(), tooltip);
        });
        
        return fuelStatistics;
    }
    
    public static Stage create(final Map<String, Map<Month, Double>> collection) {
        final Stage stage = new Stage();   
        final Scene scene = new Scene(new Group());
        final VBox root = new VBox();
        
        stage.setTitle(MainButtonNameEnum.STATISTICS.getValue());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.sizeToScene();
        stage.setResizable(Boolean.FALSE);        
             
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(ProgrammConstants.PRICE_TEXT);
        
        final CategoryAxis xAxis = new CategoryAxis();   
        xAxis.setLabel(ProgrammConstants.MONTH_TEXT);
        xAxis.setCategories(FXCollections.<String>observableArrayList(
            Stream.of(Month.values()).map(
                month -> month.getDisplayName(TextStyle.SHORT, Locale.US)
            ).collect(Collectors.toList())
        ));                  
        
        final List<?> list = collection.keySet().stream().map(
            item -> setSeries(collection, item)
        ).collect(Collectors.toList()); 
        
        final BarChart barChart = new BarChart(xAxis, yAxis);
        barChart.getData().addAll(list);        
        root.getChildren().add(barChart);
        scene.setRoot(root);
        stage.setScene(scene);
        return stage;
    }
}
