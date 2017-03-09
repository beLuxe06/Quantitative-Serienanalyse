package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.gui.model.PersonUI;

public class Stats {

	private String[][] configurationSceneMatrix;
	private String[][] configurationEpisodeMatrix;
	private String[][] configurationSeasonMatrix;
	private ArrayList<PersonUI> personsOverview;
	
	public Stats(){
	}
	
	public ArrayList<PersonUI> getPersonOverviewStats(){
		return personsOverview;
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
	
}
