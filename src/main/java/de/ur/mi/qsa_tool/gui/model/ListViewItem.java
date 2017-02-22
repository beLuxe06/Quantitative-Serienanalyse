package de.ur.mi.qsa_tool.gui.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

import de.ur.mi.qsa_tool.gui.controller.ListViewController;

public class ListViewItem
{
    @FXML
    private ImageView import_list_view_item_remove_button;
    @FXML
    private Text import_list_view_item_file_name;
    @FXML
    private ImageView import_list_view_item_file_icon;
    @FXML
    private AnchorPane list_view_item_pane;

    private ListViewController controller;
    
    public ListViewItem(ListViewController controller)
    {
    	this.controller = controller;
    	
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportListViewItem.fxml"));
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
        import_list_view_item_file_name.setText(string);
    }
    
    public AnchorPane getPane()
    {
    	return list_view_item_pane;
    }

    @FXML
	void removeItem(MouseEvent event){
    	System.out.println(((ImageView) event.getSource()).getParent().getParent().getParent().getParent().toString());
    	ImageView image = ((ImageView) event.getSource());
    	ListViewCell cell = ((ListViewCell) image.getParent().getParent().getParent().getParent());
    	controller.removeItemFromListView(cell.getIndex());
    	
	}
    
}