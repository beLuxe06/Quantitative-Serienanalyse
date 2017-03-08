package de.ur.mi.qsa_tool.gui.controller;

import java.io.IOException;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ResultScreenConfigurationMatrixController {

	
	@FXML
    private TableView<String[]> configuration_matrix;
	
	@FXML
	private AnchorPane configuration_matrix_anchor;
	
	private String[][] configurationMatrix;
	
	private AnchorPane configurationMatrixContainer;
	
	public ResultScreenConfigurationMatrixController(String[][] configurationMatrix, AnchorPane configurationMatrixContainer) {
		this.configurationMatrix = configurationMatrix;
		this.configurationMatrixContainer = configurationMatrixContainer;
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfigurationMatrix.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        updateConfigurationMatrixViewContent();
        initConfigurationMatrixView();
	}

	private void initConfigurationMatrixView(){
		configurationMatrixContainer.getChildren().add(configuration_matrix_anchor);
		configuration_matrix.refresh();
	}
	
	public void updateConfigurationMatrixViewContent() {
		ObservableList<String[]> configurationMatrixData = FXCollections.observableArrayList();
		configurationMatrixData.addAll(Arrays.asList(configurationMatrix));
		configurationMatrixData.remove(0);
		
		configuration_matrix = new TableView<>();
        //for (int i = 0; i < configurationMatrix[0].length; i++) {
		 for (int i = 0; i < 10; i++) {
            TableColumn<String[], String> tc = new TableColumn<String[], String>(configurationMatrix[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            configuration_matrix.getColumns().add(tc);
        }
        configuration_matrix.getItems().addAll(configurationMatrixData);
	}
}
