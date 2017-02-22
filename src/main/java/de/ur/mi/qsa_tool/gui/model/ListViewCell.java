package de.ur.mi.qsa_tool.gui.model;

import de.ur.mi.qsa_tool.gui.controller.ListViewController;
import javafx.scene.control.ListCell;


public class ListViewCell extends ListCell<String>
{
	
	private ListViewController controller;
	
	public ListViewCell(ListViewController controller){
		this.controller = controller;
	}
	
    @Override
    public void updateItem(String string, boolean empty)
    {
    	System.out.println("Cell Text: " + string);
        super.updateItem(string,empty);
        if(string != null)
        {
            ListViewItem item = new ListViewItem(controller);
            item.setText(string);
            setGraphic(item.getPane());
        }
        else{
        	setGraphic(null);
        }
    }
    
}