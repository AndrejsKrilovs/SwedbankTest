/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.swedbank.test.components;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import lv.swedbank.test.constants.MainButtonNameEnum;
import lv.swedbank.test.constants.ProgrammConstants;
import lv.swedbank.test.constants.TableColumnEnum;
import lv.swedbank.test.listener.ButtonEventHandler;

/**
 *
 * @author aks513
 */
public class MainClass extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle(ProgrammConstants.MAIN_APPLICATION_NAME);
        final StackPane root = new StackPane();
        root.getChildren().add(setMainPanel());
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(Boolean.FALSE);
        primaryStage.show();
    }

    private BorderPane setMainPanel() {
        final BorderPane border = new BorderPane();
        final HBox headerPanel = headerPanel();
        
        border.setTop(headerPanel);       
        border.setCenter(dataPanel());
        
        IntStream.range(
            ProgrammConstants.ONE, headerPanel.getChildren().size()
        ).mapToObj(
            index -> headerPanel.getChildren().get(index)
        ).forEach(component -> ((Button)component).setDisable(Boolean.TRUE));
        
        return border;
    }

    private HBox headerPanel() {
        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(
            ProgrammConstants.GRID_SPACE_WITH, ProgrammConstants.GRID_SPACE_HEIGHT,
            ProgrammConstants.GRID_SPACE_WITH, ProgrammConstants.GRID_SPACE_HEIGHT
        ));
        
        hbox.setSpacing(ProgrammConstants.GRID_SPACE_HEIGHT);
        
        Stream.of(MainButtonNameEnum.values()).map(
            value -> new Button(value.getValue())
        ).forEach(component -> {
            component.setOnAction(new ButtonEventHandler());
            hbox.getChildren().add(component);
        });        
        
        return hbox;
    }
    
    private VBox dataPanel() {
        final VBox grid = new VBox();
        final TableView<?> table = new TableView();
                
        Stream.of(TableColumnEnum.values()).map(
            item -> item.getValue()
        ).forEach(value -> table.getColumns().add(new TableColumn<>(value)));
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        grid.getChildren().add(table);
        return grid;
    }
}
