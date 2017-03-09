package de.ur.mi.qsa_tool.task;

import de.ur.mi.qsa_tool.util.TxtReader;
import javafx.concurrent.Task;

public class FileImportTask extends Task<String>{

	private String filepath;
	
	public FileImportTask(String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	protected String call() throws Exception {
		String fileContent = "";
		System.out.println("FileImportTask started");
		TxtReader txtReader = new TxtReader();
		System.out.println("TxtReader started");
		fileContent = txtReader.readFile(filepath);
		System.out.println("file Content size: " + fileContent.length());
		if(fileContent == ""){
			System.out.println("error! fileContent empty!");
		}
		return fileContent;
	}

}
