package de.ur.mi.qsa_tool.gui.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ListViewItem
{
    @FXML
    private ImageView import_list_view_item_remove_button;
    @FXML
    private Text import_list_view_item_file_name;
    @FXML
    private ImageView import_list_view_item_file_icon;

    public ListViewItem()
    {
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

}