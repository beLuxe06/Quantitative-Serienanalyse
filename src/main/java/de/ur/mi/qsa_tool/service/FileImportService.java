package de.ur.mi.qsa_tool.service;

import de.ur.mi.qsa_tool.task.FileImportTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FileImportService extends Service<String>{

	private String filepath;
	
	public FileImportService(String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	protected Task<String> createTask() {
		try{
			return new FileImportTask(filepath);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
