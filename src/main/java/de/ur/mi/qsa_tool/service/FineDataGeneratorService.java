package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.task.FineDataGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FineDataGeneratorService extends Service<NewData>{

	private NewData data;
	
	public FineDataGeneratorService(NewData data) {
		this.data = data;
	}
	
	@Override
	protected Task<NewData> createTask() {
		try{
			return new FineDataGeneratorTask(data);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
