package de.ur.mi.qsa_tool.gui.controller;

import java.io.IOException;
import java.util.ArrayList;

import de.ur.mi.qsa_tool.gui.model.ListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ImportListViewController {

	private ArrayList<String> filepaths;
	
	@FXML
	private ListView<String> import_list_view;
	ObservableList<String> observableImportList = FXCollections.observableArrayList(); 
	
	public ImportListViewController(ArrayList<String> filepaths) {
		this.filepaths = filepaths;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportListView.fxml"));
		fxmlLoader.setController(this);
		try{
			fxmlLoader.load();
		}
		catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public void setListView(){
		observableImportList.setAll(filepaths);
		import_list_view.setItems(observableImportList);
		import_list_view.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListViewCell();
            }
        });
	}
	
	
	
}
