package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.task.FineDataGeneratorTask;
import de.ur.mi.qsa_tool.task.FineDataGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FineDataGeneratorService extends Service<Data>{

	private Data data;
	
	public FineDataGeneratorService(Data data) {
		this.data = data;
	}
	
	@Override
	protected Task<Data> createTask() {
		try{
			return new FineDataGeneratorTask(data);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
