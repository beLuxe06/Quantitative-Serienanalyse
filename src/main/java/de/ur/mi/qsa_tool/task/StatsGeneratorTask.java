package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.model.StringIntegerPair;
import de.ur.mi.qsa_tool.util.ArrayListConverter;
import de.ur.mi.qsa_tool.util.PersonComparator;
import javafx.concurrent.Task;

public class StatsGeneratorTask extends Task <Stats>{

	private Data data;
	private Stats stats;
	
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<Person> mostImportantPersons = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	private ArrayListConverter arrayListConverter;
	private static final int NUM_OF_MOST_PERSONS = 10;
	
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
		getMostImportantPersons();
		stats.setMostImportantPersonsNames(getMostImportantPersonsNames());
		stats.setPersonOverviewStats(getPersonsOverview());
		stats.setReplyLengths(getReplyLengths());
		stats.setTimeLine(getTimeLine());
		stats.setMostWordCountsForMostImportantPersons(getMostWordsCountsForMostImportantPersons());
		//getAllScenesWordCounter();
		//getAllWordCounter();
		stats.setConfigurationSeasonMatrixList(getQuickSeasonMatrixListFromPerson());
		stats.setConfigurationEpisodeMatrixList(getQuickEpisodeMatrixListFromPerson());
		stats.setConfigurationSceneMatrixList(getQuickSceneMatrixListFromPerson());
		
		return stats;
	}
	
	
	
	private String[][] getMostWordsCountsForMostImportantPersons() {
		String[][] mostWordsForMostImportantPersons = new String[NUM_OF_MOST_PERSONS][];
		for(int i = 0; i< mostImportantPersons.size(); i++){
			Person person = mostImportantPersons.get(i);
			mostWordsForMostImportantPersons[i] = person.getMostImportantWordCounts(NUM_OF_MOST_PERSONS);
		}
		return mostWordsForMostImportantPersons;
	}

	private ArrayList<Integer> getTimeLine() {
		ArrayList<Integer> timeLine = new ArrayList<>();
		for(Scene scene : sceneList){
			if(scene.isFlashback()){
				timeLine.add(0);
			}
			else timeLine.add(1);
		}
		return timeLine;
	}

	private void getMostImportantPersons() {
		mostImportantPersons.addAll(personList);
		mostImportantPersons.sort(new PersonComparator());
		mostImportantPersons.subList(NUM_OF_MOST_PERSONS, mostImportantPersons.size()).clear();
	}

	private ArrayList<HashMap<Integer, Integer>> getReplyLengths() {
		ArrayList<HashMap<Integer, Integer>> replyLengths = new ArrayList<HashMap<Integer, Integer>>();
		for(Person person : mostImportantPersons){
			replyLengths.add(person.getReplyLengths());
		}
		return replyLengths;
	}
	
	public ArrayList<String> getMostImportantPersonsNames(){
		ArrayList<String> mostImportantPersonsNames = new ArrayList<>();
		for(int i = 0; i<mostImportantPersons.size(); i++){
			mostImportantPersonsNames.add(mostImportantPersons.get(i).getPersonId().getName());
		}
		return mostImportantPersonsNames;
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
	
	private ArrayList<String[]> getQuickSeasonMatrixListFromPerson() {
		ArrayList<String[]> returnList = new ArrayList<>();
		returnList.add(getSeasonNamesAsArray());
		for (int i = 0; i < personList.size(); i++) { 
			returnList.add(personList.get(i).getScenePresenceArray(seasonList.size()));
		}
		return returnList;
	}
	
	private ArrayList<String[]> getQuickSceneMatrixListFromPerson() {
		ArrayList<String[]> returnList = new ArrayList<>();
		returnList.add(getSceneNamesAsArray());
		for (int i = 0; i < personList.size(); i++) { 
			returnList.add(personList.get(i).getScenePresenceArray(sceneList.size()));
		}
		return returnList;
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

	private ArrayList<String[]> getQuickEpisodeMatrixListFromPerson() {
		ArrayList<String[]> returnList = new ArrayList<>();
		returnList.add(getEpisodeNamesAsArray());
		for (int i = 0; i < personList.size(); i++) { 
			returnList.add(personList.get(i).getEpisodePresenceArray(episodeList.size()));
		}
		return returnList;
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
		Integer dimension = getNeededDimension(sceneList.size(), personList.size());
		String[][] configurationMatrix = new String[dimension+1][];
		configurationMatrix [0] = getSceneNamesAsArray();
		for (int i = 0; i < sceneList.size(); i++) { 
			int rowIndex = i+1;
		    configurationMatrix[rowIndex] = sceneList.get(i).getPersonPresenceList(personList);
		}
		return configurationMatrix;
	}
	
	private String[][] getQuickSceneMatrixFromPerson() {
		Integer dimension = getNeededDimension(sceneList.size(), personList.size());
		String[][] configurationMatrix = new String[dimension+1][];
		configurationMatrix [0] = getSceneNamesAsArray();
		for (int i = 0; i < personList.size(); i++) { 
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
