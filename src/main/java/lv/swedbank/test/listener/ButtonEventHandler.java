/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import lv.swedbank.test.components.Graphics;
import lv.swedbank.test.constants.MainButtonNameEnum;
import lv.swedbank.test.constants.ProgrammConstants;
import lv.swedbank.test.constants.TableDataEnum;
import lv.swedbank.test.entity.FuelDO;

/**
 *
 * @author aks513
 */
public class ButtonEventHandler implements EventHandler<ActionEvent> {  
    
    private MainButtonNameEnum getButtonProperties(final String buttonName) {
        return Stream.of(MainButtonNameEnum.values()).filter(
                button -> button.getValue().equalsIgnoreCase(buttonName)
        ).findFirst().get();
    }

    private void initButtonComponents(final HBox headerBox, final Boolean isDisabled) {
        IntStream.range(
            ProgrammConstants.ONE, ProgrammConstants.THREE
        ).mapToObj(
            index -> headerBox.getChildren().get(index)
        ).forEach(component -> component.setDisable(isDisabled));
    }
    
    private List<FuelDO> readDataFromFile(final File file) {
        final List<FuelDO> temporaryCollection = new ArrayList<>();
        
        try(final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                final String lineString[] = line.split(ProgrammConstants.TEXT_FILE_DELIMETR);
                final String price = String.format(ProgrammConstants.FLOAT_VALUE_FORMAT, checkNumber(lineString[ProgrammConstants.ONE]));
                final String ammount = String.format(ProgrammConstants.FLOAT_VALUE_FORMAT, checkNumber(lineString[ProgrammConstants.TWO]));
                
                temporaryCollection.add(
                    new FuelDO(
                        lineString[ProgrammConstants.ZERO],
                        price.replace(ProgrammConstants.COMMA, ProgrammConstants.CURRENT_DIRECTORY_PATH),
                        ammount.replace(ProgrammConstants.COMMA, ProgrammConstants.CURRENT_DIRECTORY_PATH),
                        lineString[ProgrammConstants.THREE]
                    )
                );
            }  
        } catch (Exception ex) {
            temporaryCollection.clear();
            JOptionPane.showMessageDialog(null, ProgrammConstants.INCORRECT_FILE_EXCEPTION, null, JOptionPane.ERROR_MESSAGE);
        }  
        
        return temporaryCollection;
    }
    
    private Double checkNumber(final String stringNumber) throws NumberFormatException {        
        final String string = stringNumber.replace(ProgrammConstants.COMMA, ProgrammConstants.CURRENT_DIRECTORY_PATH);
        final Double result = Double.parseDouble(string);
        
        if(result < ProgrammConstants.ZERO)
            throw new NumberFormatException(ProgrammConstants.INCORRECT_FILE_EXCEPTION);
        else
            return result;
    }
    
    @Override
    public void handle(final ActionEvent event) {
        final Button clickedButton = (Button) event.getSource();
        final MainButtonNameEnum clickedButtonProperties = getButtonProperties(clickedButton.getText());
        final HBox headerBox = (HBox) clickedButton.getParent();        
        final FileChooser fileChooser = new FileChooser();        
        
        final TableView<FuelDO> table = (TableView<FuelDO>) (
            (VBox) headerBox.getParent().getChildrenUnmodifiable().get(ProgrammConstants.ONE)
        ).getChildren().get(ProgrammConstants.ZERO);
        final ObservableList<FuelDO> data = FXCollections.observableArrayList();
                
        switch (clickedButtonProperties) {   
            case STATISTICS: 
                final List<FuelDO> fuels = IntStream.range(
                    ProgrammConstants.ZERO, table.getItems().size()
                ).mapToObj(
                    index -> table.getItems().get(index)
                ).collect(Collectors.toList());
                
                final Stage dialog = Graphics.create(                        
                    fuels.stream().collect(Collectors.groupingBy(
                        FuelDO::getFuelName, Collectors.groupingBy(FuelDO::getDateValue, Collectors.summingDouble(FuelDO::getTotalPrice))
                    ))
                );
                dialog.show();
                break;
            case CLEAR_DATA: 
                table.getItems().clear();
                headerBox.getChildren().get(ProgrammConstants.ZERO).setDisable(Boolean.FALSE);
                initButtonComponents(headerBox, Boolean.TRUE);
                break;
            case LOAD_DATA:
                final File file = fileChooser.showOpenDialog(null);
                                                
                if(file != null) {
                    final List<FuelDO> fuelList = readDataFromFile(file);
                    
                    if(fuelList.size() > ProgrammConstants.ZERO) { 
                        try {
                            data.addAll(fuelList);
                            clickedButton.setDisable(Boolean.TRUE);
                            initButtonComponents(headerBox, Boolean.FALSE);
                        } catch(Exception e) {
                            JOptionPane.showMessageDialog(null, ProgrammConstants.INCORRECT_FILE_EXCEPTION, null, JOptionPane.ERROR_MESSAGE);
                        }                        
                    } else
                        initButtonComponents(headerBox, Boolean.TRUE);
                }
                
                final String dataProperties [] = Stream.of(TableDataEnum.values()).map(value -> value.getValue()).toArray(String[]::new);
      
                IntStream.rangeClosed(
                    ProgrammConstants.ZERO, ProgrammConstants.THREE
                ).forEach(index -> (
                    (TableColumn)table.getColumns().get(index)
                ).setCellValueFactory(new PropertyValueFactory<>(dataProperties[index])));
                
                table.setItems(data);
                break;
        }
    }
}
