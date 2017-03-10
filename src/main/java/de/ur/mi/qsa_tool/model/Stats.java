package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

import de.ur.mi.qsa_tool.gui.model.PersonUI;

public class Stats {

	private String[][] configurationSceneMatrix;
	private String[][] configurationEpisodeMatrix;
	private String[][] configurationSeasonMatrix;
	private ArrayList<String> mostImportantPersonsNames;
	private ArrayList<PersonUI> personsOverview;
	private ArrayList<HashMap<Integer, Integer>> replyLengths;
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

	public String[][] getConfigurationSceneMatrix() {
		return configurationSceneMatrix;
	}

	public void setConfigurationSceneMatrix(String[][] configurationMatrix) {
		this.configurationSceneMatrix = configurationMatrix;
	}

	public String[][] getConfigurationSeasonMatrix() {
		return configurationSeasonMatrix;
	}

	public void setConfigurationSeasonMatrix(String[][] configurationSeasonMatrix) {
		this.configurationSeasonMatrix = configurationSeasonMatrix;
	}

	public String[][] getConfigurationEpisodeMatrix() {
		return configurationEpisodeMatrix;
	}

	public void setConfigurationEpisodeMatrix(String[][] configurationEpisodeMatrix) {
		this.configurationEpisodeMatrix = configurationEpisodeMatrix;
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
	
}
