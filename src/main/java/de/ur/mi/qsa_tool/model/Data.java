package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class Data {

	private Corpus corpus;
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	private Integer actionCount = 0;
	
	public Integer getActionCount() {
		return actionCount;
	}

	public void setActionCount(Integer actionCount) {
		this.actionCount = actionCount;
	}
	
	public Person getPersonFromId(String personID){
		for(Person person : personList){
			if(person.getPersonId().getId().equals(personID)){
				return person;
			}
		}
		return null;
	}
	
	public Person getPersonFromName(String name){
		for(Person person : personList){
			if(person.getPersonId().getName().equals(name)){
				return person;
			}
		}
		return null;
	}

	public ArrayList<Action> getActionList() {
		return actionList;
	}

	public void setActionList(ArrayList<Action> actionList) {
		this.actionList = actionList;
	}

	public Corpus getCorpus() {
		return corpus;
	}
	
	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}
	
	public ArrayList<Person> getPersonList() {
		return personList;
	}
	
	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
	
	public ArrayList<Scene> getSceneList() {
		return sceneList;
	}
	
	public void setSceneList(ArrayList<Scene> sceneList) {
		this.sceneList = sceneList;
	}
	
	public ArrayList<Episode> getEpisodeList() {
		return episodeList;
	}
	
	public void setEpisodeList(ArrayList<Episode> episodeList) {
		this.episodeList = episodeList;
	}
	
	public ArrayList<Season> getSeasonList() {
		return seasonList;
	}
	
	public void setSeasonList(ArrayList<Season> seasonList) {
		this.seasonList = seasonList;
	}

	@Override
	public String toString() {
		return "NewData values: fileNames: " + corpus.getFileNames().toString() + " found persons: " + personList.size() + " found actions: " + actionList.size() + 
				" found scenes: " + sceneList.size() +" found episodes: " + episodeList.size() + " found seasons: " + seasonList.size();
	}

	public void clear() {
		corpus.clear();
		personList.clear();
		sceneList.clear();
		episodeList.clear();
		seasonList.clear();
		actionList.clear();
	}
	
	
	
}
