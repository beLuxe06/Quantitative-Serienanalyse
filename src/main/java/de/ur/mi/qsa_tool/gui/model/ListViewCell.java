package de.ur.mi.qsa_tool.gui.model;

import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<String>
{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null)
        {
            ListViewItem item = new ListViewItem();
            item.setText(string);
        }
    }
}