package de.ur.mi.qsa_tool.service;


import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RawDataGeneratorService extends Service<Data> {

	private Data data;
	
	public RawDataGeneratorService(Data data) {
		this.data = data;
	}
	
	@Override
	protected Task<Data> createTask() {
		try{
			return new RawDataGeneratorTask(data);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
