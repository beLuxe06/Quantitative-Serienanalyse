package de.ur.mi.qsa_tool.gui.model;

import de.ur.mi.qsa_tool.gui.controller.ResultScreenListViewController;
import javafx.scene.control.ListCell;


public class ResultScreenListViewCell extends ListCell<String>
{
	
	private ResultScreenListViewController controller;
	
	public ResultScreenListViewCell(ResultScreenListViewController controller){
		this.controller = controller;
	}
	
    @Override
    public void updateItem(String string, boolean empty)
    {
    	System.out.println("Cell Text: " + string);
        super.updateItem(string,empty);
        if(string != null)
        {
            ResultScreenListViewItem item = new ResultScreenListViewItem(controller);
            item.setText(string);
            setGraphic(item.getPane());
        }
        else{
        	setGraphic(null);
        }
    }
    
}