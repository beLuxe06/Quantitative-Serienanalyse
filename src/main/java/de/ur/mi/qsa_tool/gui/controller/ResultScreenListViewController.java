package de.ur.mi.qsa_tool.gui.controller;

import java.io.IOException;

import de.ur.mi.qsa_tool.gui.model.ResultScreenListViewCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ResultScreenListViewController {
	
	@FXML
	private ListView<String> result_list_view;
	
	@FXML
	private AnchorPane result_list_container;
	
	private ObservableList<String> observableItemList;
	private AnchorPane listViewContainer;
	
	public ResultScreenListViewController(ObservableList<String> observableItemList, AnchorPane listViewContainer) {
		this.observableItemList = observableItemList;
		this.listViewContainer = listViewContainer;
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResultListView.fxml"));
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
		listViewContainer.getChildren().add(result_list_container);
	}
	
	public ObservableList<String> getItems() {
		return result_list_view.getItems();
	}
	
	public void updateListViewContent() {
		result_list_view.setItems(observableItemList);
		result_list_view.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ResultScreenListViewCell(ResultScreenListViewController.this);
            }
        });
	}
	
	public void removeItemFromListView(int itemIndex){
		observableItemList.remove(itemIndex);
		result_list_view.refresh();
	}
}
