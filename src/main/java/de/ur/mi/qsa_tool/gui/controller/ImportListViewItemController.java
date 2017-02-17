package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ImportListViewItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView import_list_view_item_file_icon;

    @FXML
    private ImageView import_list_view_item_remove_button;

    @FXML
    private Text import_list_view_item_file_name;

    @FXML
    void removeListViewItem(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert import_list_view_item_file_icon != null : "fx:id=\"import_list_view_item_file_icon\" was not injected: check your FXML file 'ImportListViewItem.fxml'.";
        assert import_list_view_item_remove_button != null : "fx:id=\"import_list_view_item_remove_button\" was not injected: check your FXML file 'ImportListViewItem.fxml'.";
        assert import_list_view_item_file_name != null : "fx:id=\"import_list_view_item_file_name\" was not injected: check your FXML file 'ImportListViewItem.fxml'.";

    }
}
