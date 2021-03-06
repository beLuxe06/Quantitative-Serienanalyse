package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import javafx.concurrent.Task;

public class RawDataGeneratorTask extends Task <Data>{

	private Data data;
	
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	
	private int sceneCounter = 0;
	private int actionCounter = 0;
	private int episodeCounter = 1;
	private int seasonCounter = 1;
	private int personCounter = 0;
	
	private static final char ACTION_STARTER = '(';
	//private static final char ACTION_STOPPER = ')';
	private static final char SCENE_STARTER = '[';
	//private static final char SCENE_STOPPER = ']';
	private static final char EPISODE_STARTER = '-';
	private static final char SEASON_STARTER = '~';
	private static final String MISTAKE = "﻿";
	
	public RawDataGeneratorTask(Data data) {
		this.data = data;
	}
	
	@Override
	protected Data call() throws Exception {
			ArrayList<String> fileContent = new ArrayList<String>(getFileContents());
			System.out.println("Raw Data: processed fileContentSize: " + fileContent.size());
			
			for (int i = 0; i<fileContent.size(); i++) {
				String file = fileContent.get(i);
				String[] lines = file.split("\\n");
				System.out.println("lines size: " + lines.length + " of file no.: " + (fileContent.indexOf(file)+1));
				for(String line : lines) {
					if(line.contains(MISTAKE)){
						line = line.replaceAll(MISTAKE, "");
					}
					char c = line.charAt(0);
					
					// check for scene
					if (c == SCENE_STARTER) {
						sceneCounter++;
						sceneList.add(new Scene(seasonCounter, episodeCounter, sceneCounter));
						sceneList.get(sceneCounter-1).setTitle(line);
					} 
					// check for action
					else if (c == ACTION_STARTER) {
						actionCounter++;
					} 
					// check for episode
					else if (c == EPISODE_STARTER) {
						episodeList.add(new Episode(seasonCounter, episodeCounter));
						episodeCounter++;
					} 
					// check for season
					else if (c == SEASON_STARTER) {
						seasonList.add(new Season(seasonCounter));
						seasonCounter++;
					} 
					else {
						int charCounter = 0;
						while (c != ':' && charCounter < line.length() - 1) {
							charCounter++;
							c = line.charAt(charCounter);
						}
						// check for person
						if (c == ':') {
							String personName = line.substring(0, charCounter);
							if(!personNames.contains(personName)){
								personCounter++;
								personNames.add(personName);
								personList.add(new Person(personCounter, personName));
							}
						}
					}
					
				}
			}
		
		System.out.println("Found Seasons: " + seasonList.size());
		createData();
		
		return data;
	}
	
	

	@Override
	protected void failed() {
		super.failed();
		throw new RuntimeException(this.getException());
	}

	private ArrayList<String> getFileContents() {
		ArrayList<String> fileContents = new ArrayList<>();
		for(int i = 0; i < data.getCorpus().getScripts().size(); i++){
			fileContents.add(data.getCorpus().getScripts().get(i).getFileContent());
		}
		return fileContents;
	}

	private void createData() {
		data.setPersonList(personList);
		System.out.println("found persons: " + personList.size());
		data.setEpisodeList(episodeList);
		System.out.println("found episodes: " + episodeList.size());
		data.setSceneList(sceneList);
		System.out.println("found scenes: " + sceneList.size());
		data.setSeasonList(seasonList);
		System.out.println("found seasons: " + seasonList.size());
		data.setActionList(actionList);
		System.out.println("found actions: " + actionCounter);
		data.setActionCount(actionCounter);
		System.out.println("creating raw Data...");
	}

	
}
