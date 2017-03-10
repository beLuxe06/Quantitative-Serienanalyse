package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;
import java.util.HashMap;

import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.model.Stats;
import de.ur.mi.qsa_tool.util.ArrayListConverter;
import de.ur.mi.qsa_tool.util.PersonComparator;
import javafx.concurrent.Task;

public class StatsGeneratorTask extends Task <Stats>{

	private Data data;
	private Stats stats;
	
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<PersonUI> mostImportantPersonsUI = new ArrayList<>();
	private ArrayList<Person> mostImportantPersons = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	private ArrayListConverter arrayListConverter;
	private static final int NUM_OF_MOST_PERSONS = 6;
	
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
		stats.setMostImportantPersons(mostImportantPersonsUI);
		stats.setPersonOverviewStats(getPersonsOverview());
		System.out.println("person overview generated!");
		stats.setReplyLengthsMostImportant(getReplyLengthsMostImportant());
		stats.setReplyLengths(getReplyLengths());
		System.out.println("reply lengths generated!");
		stats.setTimeLine(getTimeLine());
		System.out.println("timeline generated!");
		stats.setMostWordCountsForMostImportantPersons(getMostWordsCountsForMostImportantPersons());
		stats.setWordCountsForPersons(getWordsCountsForPersons());
		System.out.println("word counts generated!");
		stats.setPersonConstellations(getPersonConstellations());
		System.out.println("person constellations generated!");
		//getAllScenesWordCounter();
		//getAllWordCounter();
		stats.setConfigurationSeasonMatrixList(getQuickSeasonMatrixListFromPerson());
		stats.setConfigurationEpisodeMatrixList(getQuickEpisodeMatrixListFromPerson());
		stats.setConfigurationSceneMatrixList(getQuickSceneMatrixListFromPerson());
		System.out.println("configuration matrices generated!");
		
		return stats;
	}
	
	
	
	private Integer[][] getPersonConstellations() {
		Integer personLength = personList.size();
		Integer constellationTable [][] = new Integer [personLength][personLength];
		for(int i=0;i<personLength;i++){
			for(int j=0;j<personLength;j++){
				int countSameScenes = 0;
				if(i!=j){
					for(int k=0;k<personList.get(i).getSceneIdList().size();k++){
						for(int l=0;l<personList.get(j).getSceneIdList().size();l++){
							if(personList.get(i).getSceneIdList().get(k) == personList.get(j).getSceneIdList().get(l)){
								countSameScenes++;							
							}
						}
					}
				}
				float temp = (float) ((countSameScenes)/(float)(personList.get(i).getSceneIdList().size()))*100;
				constellationTable[i][j]= (int) (temp);
			}
		}
		return constellationTable;
	}

	private String[][] getWordsCountsForPersons() {
		String[][] wordsForPersons = new String[personList.size()][];
		for(int i = 0; i< mostImportantPersons.size(); i++){
			Person person = mostImportantPersons.get(i);
			wordsForPersons[i] = person.getMostImportantWordCounts2(personList.size());
		}
		return wordsForPersons;
	}

	private String[][] getMostWordsCountsForMostImportantPersons() {
		String[][] mostWordsForMostImportantPersons = new String[NUM_OF_MOST_PERSONS][];
		for(int i = 0; i< mostImportantPersons.size(); i++){
			Person person = mostImportantPersons.get(i);
			mostWordsForMostImportantPersons[i] = person.getMostImportantWordCounts2(NUM_OF_MOST_PERSONS);
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
		mostImportantPersonsUI =  arrayListConverter.convertPersonListToPersonStats(mostImportantPersons, seasonList.size(), sceneList.size(), episodeList.size());
	}

	private ArrayList<HashMap<Integer, Integer>> getReplyLengthsMostImportant() {
		ArrayList<HashMap<Integer, Integer>> replyLengths = new ArrayList<HashMap<Integer, Integer>>();
		for(Person person : mostImportantPersons){
			replyLengths.add(person.getReplyLengths());
		}
		return replyLengths;
	}
	
	private ArrayList<HashMap<Integer, Integer>> getReplyLengths() {
		ArrayList<HashMap<Integer, Integer>> replyLengths = new ArrayList<HashMap<Integer, Integer>>();
		for(Person person : personList){
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
