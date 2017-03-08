package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.model.Stats;
import javafx.concurrent.Task;

public class StatsGeneratorTask extends Task <Stats>{

	private Data data;
	private Stats stats;
	
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	
	public StatsGeneratorTask(Data data) {
		this.data = data;
		readDataValues();
	}
	
	private void readDataValues() {
		personList = data.getPersonList();
		sceneList = data.getSceneList();
		seasonList = data.getSeasonList();
		episodeList = data.getEpisodeList();
		actionList = data.getActionList();
	}
	
	@Override
	protected Stats call() throws Exception {
		stats = new Stats();
		getPersonNames();
		//getTimeLine();
		//getAllScenesWordCounter();
		//getReplyLengths();
		//getAllWordCounter();
		stats.setConfigurationMatrix(getQuickMatrix());
		
		return stats;
	}
	
	private void getPersonNames() {
		for(int i = 0; i < personList.size(); i++){
			personNames.add(personList.get(i).getPersonId().getName());
		}
	}
	
	private String[][] getQuickMatrix() {
		String[][] configurationMatrix = new String[sceneList.size()+1][];
		configurationMatrix [0] = getSceneNamesAsArray();
		for (int i = 0; i < personList.size(); i++) { 
		    configurationMatrix[i] = personList.get(i).getScenePresenceArray(sceneList.size());
		}
		return configurationMatrix;
	}
	
	private String[] getSceneNamesAsArray(){
		String[] sceneNamesAsArray = new String[sceneList.size()+1];
		sceneNamesAsArray[0] = "Person:";
		for(int i = 0; i < sceneList.size(); i++){
			sceneNamesAsArray [i] = "" + sceneList.get(i).getSceneId();
		}
		return sceneNamesAsArray;
	}
	
}
