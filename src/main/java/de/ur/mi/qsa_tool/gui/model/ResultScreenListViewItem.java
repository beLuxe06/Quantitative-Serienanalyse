package de.ur.mi.qsa_tool.gui.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.ur.mi.qsa_tool.gui.controller.ResultScreenListViewController;

public class ResultScreenListViewItem
{
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane diagram_view_content;

    @FXML
    private ImageView diagram_view_item_icon;

    @FXML
    private BorderPane result_list_view_item_pane;

    @FXML
    private ImageView diagram_view_download_button;

    @FXML
    private Text diagram_view_item_title;

    private ResultScreenListViewController controller;
    
    public ResultScreenListViewItem(ResultScreenListViewController controller)
    {
    	this.controller = controller;
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DiagramListViewItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public void setText(String string)
    {
    	System.out.println("Item Text: " + string);
        diagram_view_item_title.setText(string);
    }
    
    public BorderPane getPane()
    {
    	return result_list_view_item_pane;
    }
    
    public void setContent(Node content){
    	diagram_view_content.getChildren().add(content);
    }

    @FXML
    void downloadDiagram(MouseEvent event) {

    }
    
    @FXML
    void initialize() {
        assert diagram_view_content != null : "fx:id=\"diagram_view_content\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_item_icon != null : "fx:id=\"diagram_view_item_icon\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert result_list_view_item_pane != null : "fx:id=\"result_list_view_item_pane\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_download_button != null : "fx:id=\"diagram_view_download_button\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
        assert diagram_view_item_title != null : "fx:id=\"diagram_view_item_title\" was not injected: check your FXML file 'DiagramViewItem.fxml'.";
    }
    
}