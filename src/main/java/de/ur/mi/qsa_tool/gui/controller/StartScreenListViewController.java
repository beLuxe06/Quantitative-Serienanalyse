package de.ur.mi.qsa_tool.gui.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import de.ur.mi.qsa_tool.gui.model.StartScreenListViewCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class StartScreenListViewController {
	
	@FXML
	private ListView<String> import_files_list_view;
	
	@FXML
	private AnchorPane import_list_container;
	
	private ObservableList<String> observableItemList;
	private HBox listViewContainer;
	
	public StartScreenListViewController(ObservableList<String> observableItemList, HBox listViewContainer) {
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
		listViewContainer.getChildren().add(import_list_container);
	}
	
	public ObservableList<String> getItems() {
		return import_files_list_view.getItems();
	}

	public void updateListViewContent() {
		sortObservableItemList();
		import_files_list_view.setItems(observableItemList);
		import_files_list_view.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new StartScreenListViewCell(StartScreenListViewController.this);
            }
        });
	}
	
	private void sortObservableItemList() {
		if(observableItemList.size() > 1){
			observableItemList = observableItemList.sorted(Comparator.<String> naturalOrder());
		}
	}

	public void removeItemFromListView(int itemIndex){
		observableItemList.remove(itemIndex);
		updateListViewContent();
	}
}
