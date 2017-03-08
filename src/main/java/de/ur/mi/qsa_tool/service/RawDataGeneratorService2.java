package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask2;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RawDataGeneratorService2 extends Service<NewData> {

	private NewData newData;
	
	public RawDataGeneratorService2(NewData newData) {
		this.newData = newData;
	}
	
	@Override
	protected Task<NewData> createTask() {
		try{
			return new RawDataGeneratorTask2(newData);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}