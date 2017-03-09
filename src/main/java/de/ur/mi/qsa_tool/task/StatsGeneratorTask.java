package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.util.ArrayListConverter;
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
	private ArrayListConverter arrayListConverter;
	
	public StatsGeneratorTask(Data data) {
		this.data = data;
		readDataValues();
		arrayListConverter = new ArrayListConverter();
	}
	
	private void readDataValues() {
		this.personList = data.getPersonList();
		this.sceneList = data.getSceneList();
		this.seasonList = data.getSeasonList();
		this.episodeList = data.getEpisodeList();
		this.actionList = data.getActionList();
	}
	
	@Override
	protected Stats call() throws Exception {
		stats = new Stats();
		getPersonNames();
		stats.setPersonOverviewStats(getPersonsOverview());
		//getTimeLine();
		//getAllScenesWordCounter();
		//getReplyLengths();
		//getAllWordCounter();
		//stats.setConfigurationSceneMatrix(getQuickSceneMatrix());
		//stats.setConfigurationSeasonMatrix(getQuickSeasonMatrix());
		//stats.setConfigurationSceneMatrix(getQuickSceneMatrixFromPerson());
		stats.setConfigurationSceneMatrix(getQuickSceneMatrixFromScene());
		//stats.setConfigurationEpisodeMatrix(getQuickEpisodeMatrixFromPerson());
		
		return stats;
	}
	
	
	
	@Override
	protected void failed() {
		super.failed();
	}

	private ArrayList<PersonUI> getPersonsOverview() {
		return arrayListConverter.convertPersonListToPersonStats(personList, seasonList.size(), sceneList.size(), episodeList.size());
	}

	private void getPersonNames() {
		for(int i = 0; i < personList.size(); i++){
			personNames.add(personList.get(i).getPersonId().getName());
		}
	}
	
	private String[][] getQuickSeasonMatrixFromPerson() {
		String[][] configurationMatrix = new String[seasonList.size()+1][personList.size()+1];
		configurationMatrix [0] = getSeasonNamesAsArray();
		for (int i = 0; i < personList.size(); i++) { 
			int rowIndex = i+1;
		    configurationMatrix[rowIndex] = personList.get(i).getSeasonPresenceArray(seasonList.size());
		}
		return configurationMatrix;
	}
	
	private String[] getSeasonNamesAsArray() {
		String[] seasonNamesAsArray = new String[seasonList.size()+1];
		seasonNamesAsArray[0] = "Person:";
		for(int i = 0; i < seasonList.size(); i++){
			int columnIndex = i+1;
			seasonNamesAsArray [columnIndex] = "Staffel: " + seasonList.get(i).getSeasonId();
		}
		return seasonNamesAsArray;
	}

	private String[][] getQuickEpisodeMatrixFromPerson() {
		String[][] configurationMatrix = new String[episodeList.size()+1][personList.size()+1];
		configurationMatrix [0] = getEpisodeNamesAsArray();
		for (int i = 0; i < episodeList.size(); i++) { 
			int rowIndex = i;
			rowIndex++;
		    configurationMatrix[rowIndex] = personList.get(i).getEpisodePresenceArray(episodeList.size());
		}
		return configurationMatrix;
	}
	
	private String[] getEpisodeNamesAsArray() {
		String[] episodeNamesAsArray = new String[episodeList.size()+1];
		episodeNamesAsArray[0] = "Person:";
		for(int i = 0; i < episodeList.size(); i++){
			int columnIndex = i+1;
			episodeNamesAsArray [columnIndex] = "Episode: " + episodeList.get(i).getEpisodeId();
		}
		return episodeNamesAsArray;
	}

	private String[][] getQuickSceneMatrixFromScene() {
		String[][] configurationMatrix = new String[sceneList.size()+1][];
		configurationMatrix [0] = getSceneNamesAsArray();
		for (int i = 0; i < sceneList.size(); i++) { 
			int colIndex = i+1;
		    configurationMatrix[colIndex] = sceneList.get(i).getPersonPresenceList(personList);
		}
		return configurationMatrix;
	}
	
	private String[][] getQuickSceneMatrixFromPerson() {
		String[][] configurationMatrix = new String[sceneList.size()+1][personList.size()+1];
		configurationMatrix [0] = getSceneNamesAsArray();
		for (int i = 0; i < sceneList.size(); i++) { 
			int rowIndex = i+1;
		    configurationMatrix[rowIndex] = personList.get(i).getScenePresenceArray(sceneList.size());
		}
		return configurationMatrix;
	}
	
	private String[] getSceneNamesAsArray(){
		String[] sceneNamesAsArray = new String[sceneList.size()+1];
		sceneNamesAsArray[0] = "Person:";
		for(int i = 0; i < sceneList.size(); i++){
			int columnIndex = i+1;
			sceneNamesAsArray [columnIndex] = "Szene: " + sceneList.get(i).getSceneId();
		}
		return sceneNamesAsArray;
	}
	
}
