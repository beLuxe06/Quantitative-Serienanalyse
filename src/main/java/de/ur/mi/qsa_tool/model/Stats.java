package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

import de.ur.mi.qsa_tool.gui.model.PersonUI;

public class Stats {

	private ArrayList<String[]> configurationEpisodeMatrixList;
	private ArrayList<String[]> configurationSceneMatrixList;
	private ArrayList<String[]> configurationSeasonMatrixList;
	private ArrayList<String> mostImportantPersonsNames;
	private ArrayList<PersonUI> personsOverview;
	private ArrayList<PersonUI> mostImportantPersons;
	private ArrayList<HashMap<Integer, Integer>> replyLengths;
	private ArrayList<HashMap<Integer, Integer>> replyLengthsMostImportant;
	private String[][] wordsForPersons;
	private String[][] mostImportantWordsForMostImportantPersons;
	private ArrayList<Integer> timeLine;

	public ArrayList<Integer> getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(ArrayList<Integer> timeLine) {
		this.timeLine = timeLine;
	}

	public Stats(){
	}
	
	public ArrayList<PersonUI> getPersonOverviewStats(){
		return personsOverview;
	}
	
	public ArrayList<HashMap<Integer, Integer>> getReplyLengths() {
		return replyLengths;
	}

	public void setReplyLengths(ArrayList<HashMap<Integer, Integer>> replyLengths) {
		this.replyLengths = replyLengths;
	}
	
	public void setPersonOverviewStats(ArrayList<PersonUI> personUIList){
		this.personsOverview = personUIList;
	}

	public void setMostImportantPersonsNames(ArrayList<String> mostImportantPersonsNames) {
		this.mostImportantPersonsNames = mostImportantPersonsNames;
	}
	
	public ArrayList<String> getMostImportantPersonsNames() {
		return mostImportantPersonsNames;
	}

	public void setMostWordCountsForMostImportantPersons(String[][] mostWordsCountsForMostImportantPersons) {
		this.mostImportantWordsForMostImportantPersons = mostWordsCountsForMostImportantPersons;
	}
	
	public String[][] getMostWordCountsForMostImportantPersons() {
		return mostImportantWordsForMostImportantPersons;
	}

	public ArrayList<String[]> getConfigurationSeasonMatrixList() {
		return configurationSeasonMatrixList;
	}

	public void setConfigurationSeasonMatrixList(ArrayList<String[]> configurationSeasonMatrixList) {
		this.configurationSeasonMatrixList = configurationSeasonMatrixList;
	}
	
	public void setConfigurationEpisodeMatrixList(ArrayList<String[]> quickEpisodeMatrixListFromPerson) {
		this.configurationEpisodeMatrixList = quickEpisodeMatrixListFromPerson;
		
	}

	public ArrayList<String[]> getConfigurationEpisodeMatrixList() {
		return configurationEpisodeMatrixList;
	}

	public void setConfigurationSceneMatrixList(ArrayList<String[]> quickSceneMatrixListFromPerson) {
		this.configurationSceneMatrixList = quickSceneMatrixListFromPerson;
		
	}
	public ArrayList<String[]> getConfigurationSceneMatrixList() {
		return configurationSceneMatrixList;
	}

	public void clear() {
		configurationEpisodeMatrixList.clear();
		configurationSceneMatrixList.clear();
		configurationSeasonMatrixList.clear();
		mostImportantPersonsNames.clear();
		personsOverview.clear();
		replyLengths.clear();
		timeLine.clear();
	}

	public void setMostImportantPersons(ArrayList<PersonUI> mostImportantPersons) {
		this.mostImportantPersons = mostImportantPersons;
	}
	
	public ArrayList<PersonUI> getMostImportantPersons() {
		return mostImportantPersons;
	}

	public void setReplyLengthsMostImportant(ArrayList<HashMap<Integer, Integer>> replyLengthsMostImportant) {
		this.replyLengthsMostImportant = replyLengthsMostImportant;
	}
	
	public ArrayList<HashMap<Integer, Integer>> getReplyLengthsMostImportant() {
		return replyLengthsMostImportant;
	}

	public void setWordCountsForPersons(String[][] wordsCountsForPersons) {
		this.wordsForPersons = wordsCountsForPersons;
		
	}
	
	public String[][] getWordsForPersons() {
		return wordsForPersons;
	}
	
}
