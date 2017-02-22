package de.ur.mi.qsa_tool.gui.controller;

import java.io.IOException;

import de.ur.mi.qsa_tool.gui.model.ListViewCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ListViewController {
	
	@FXML
	private ListView<String> import_files_list_view;
	
	@FXML
	private AnchorPane container;
	
	private ObservableList<String> observableItemList;
	private HBox listViewContainer;
	
	public ListViewController(ObservableList<String> observableItemList, HBox listViewContainer) {
		this.observableItemList = observableItemList;
		this.listViewContainer = listViewContainer;
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportListView.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        initListView();
        updateListViewContent();
	}

	private void initListView(){
		listViewContainer.getChildren().add(container);
	}
	
	public ObservableList<String> getItems() {
		return import_files_list_view.getItems();
	}

	public void updateListViewContent() {
		import_files_list_view.setItems(observableItemList);
		import_files_list_view.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListViewCell(ListViewController.this);
            }
        });
	}
	
	public void removeItemFromListView(int itemIndex){
		observableItemList.remove(itemIndex);
		import_files_list_view.refresh();
	}
}
