package de.ur.mi.qsa_tool.gui.model;

import de.ur.mi.qsa_tool.gui.controller.StartScreenListViewController;
import javafx.scene.control.ListCell;


public class StartScreenListViewCell extends ListCell<String>
{
	
	private StartScreenListViewController controller;
	
	public StartScreenListViewCell(StartScreenListViewController controller){
		this.controller = controller;
	}
	
    @Override
    public void updateItem(String string, boolean empty)
    {
    	System.out.println("Cell Text: " + string);
        super.updateItem(string,empty);
        if(string != null)
        {
            StartScreenListViewItem item = new StartScreenListViewItem(controller);
            item.setText(string);
            setGraphic(item.getPane());
        }
        else{
        	setGraphic(null);
        }
    }
    
}