package de.ur.mi.qsa_tool.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileInputChecker {

	public void updateArrayListFromFilePathsAsString(String filepathsAsString, ArrayList<String> actualInputFiles) {
		ArrayList<String> filepaths = getFilePathArrayListFromSingleString(filepathsAsString);
		for(String filepath : filepaths){
			if(!actualInputFiles.contains(filepath) && !filepath.isEmpty() && filepath.endsWith(".txt") && isFile(filepath)){
				actualInputFiles.add(filepath);
				System.out.println("File to add: " + filepath);
			}
		}
	}

	private boolean isFile(String filepath) {
		File f = new File(filepath);
		if(f.exists() && !f.isDirectory()){
			return true;
		}
		else return false;
	}

	public String getAllPathsAsSingleString(ArrayList<String> filepaths) {
		String inputFilePathsAsSingleString = "";
		for(String filepath: filepaths){
			if(!filepath.isEmpty()){
				inputFilePathsAsSingleString = inputFilePathsAsSingleString.concat(filepath + ";");
			}
		}
		System.out.println("FileList as single String: " + inputFilePathsAsSingleString);
		return inputFilePathsAsSingleString;
	}

	public ArrayList<String> getFilePathArrayListFromSingleString(String inputFilePathsAsSingleString) {
		ArrayList<String> filepaths = new ArrayList<>();
		filepaths.addAll(Arrays.asList(inputFilePathsAsSingleString.split(";")));
		for(String filepath: filepaths){
			filepath = filepath.replace(";", "");
			filepath = filepath.trim();
		}
		deleteEmptyStringsInFilePathLists(filepaths);
		System.out.println("FileList from single String: " + filepaths.toString());
		return filepaths;
	}

	private void deleteEmptyStringsInFilePathLists(ArrayList<String> filepaths) {
		String[] emptyString = new String[]{" "};
		filepaths.removeAll(Arrays.asList(emptyString));
	}
	
}
