package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RawDataGeneratorService extends Service<Data> {

	private Data newData;
	
	public RawDataGeneratorService(Data newData) {
		this.newData = newData;
	}
	
	@Override
	protected Task<Data> createTask() {
		try{
			return new RawDataGeneratorTask(newData);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
