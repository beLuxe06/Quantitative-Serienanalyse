package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Corpus {
	
	private ArrayList<Script> scripts = new ArrayList<>();

	public Corpus(ArrayList<Script> scripts) {
		this.scripts = scripts;
	}

	public ArrayList<Script> getScripts() {
		return scripts;
	}

	public void setScripts(ArrayList<Script> scripts) {
		this.scripts = scripts;
	}
	
	public ArrayList<String> getFileNames(){
		ArrayList<String> fileNames = new ArrayList<>();
		for(int i = 0; i < scripts.size(); i++){
			fileNames.add(scripts.get(i).getFileName());
		}
		return fileNames;
	}
	
	public ArrayList<String> getFilePaths(){
		ArrayList<String> filePaths = new ArrayList<>();
		for(int i = 0; i < scripts.size(); i++){
			filePaths.add(scripts.get(i).getFilePath());
		}
		return filePaths;
	}
	
	public ArrayList<String> getFileContents(){
		ArrayList<String> fileContents = new ArrayList<>();
		for(int i = 0; i < scripts.size(); i++){
			fileContents.add(scripts.get(i).getFileContent());
		}
		return fileContents;
	}
	
	
}
