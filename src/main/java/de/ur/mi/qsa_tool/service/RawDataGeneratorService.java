package de.ur.mi.qsa_tool.service;

import java.util.List;

import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RawDataGeneratorService extends Service<NewData> {

	private NewData newData;
	
	public RawDataGeneratorService(NewData newData) {
		this.newData = newData;
	}
	
	@Override
	protected Task<NewData> createTask() {
		try{
			return new RawDataGeneratorTask(newData);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
