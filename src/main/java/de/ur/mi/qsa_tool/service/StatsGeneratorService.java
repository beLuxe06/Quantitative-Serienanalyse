package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.task.RawDataGeneratorTask2;
import de.ur.mi.qsa_tool.task.StatsGeneratorTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class StatsGeneratorService extends Service<Stats> {

	private NewData newData;
	
	public StatsGeneratorService(NewData newData) {
		this.newData = newData;
	}
	
	@Override
	protected Task<Stats> createTask() {
		try{
			return new StatsGeneratorTask(newData);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}