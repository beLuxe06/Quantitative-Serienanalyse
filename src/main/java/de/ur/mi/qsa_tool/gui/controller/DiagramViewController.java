package de.ur.mi.qsa_tool.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DiagramViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane diagram_view_content;

    @FXML
    private ImageView diagram_view_item_icon;

    @FXML
    private ImageView diagram_view_download_button;

    @FXML
    private Text diagram_view_item_title;

    @FXML
    void downloadDiagram(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert diagram_view_content != null : "fx:id=\"diagram_view_content\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_item_icon != null : "fx:id=\"diagram_view_item_icon\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_download_button != null : "fx:id=\"diagram_view_download_button\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_item_title != null : "fx:id=\"diagram_view_item_title\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";

    }
}
