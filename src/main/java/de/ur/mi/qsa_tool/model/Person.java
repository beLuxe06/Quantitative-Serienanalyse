package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	
	private PersonId personId;
	private ArrayList<Integer> seasonIdList = new ArrayList<>();
	private ArrayList<Integer> episodeIdList = new ArrayList<>();
	private ArrayList<Integer> sceneIdList = new ArrayList<>();
	private ArrayList<ReplyLength> replyLengths = new ArrayList<>();
	private ArrayList<HashMap<ScriptId, WordCount>> wordCounts = new ArrayList<>();
	
	public Person(PersonId personId, ArrayList<Integer> seasonIdList, ArrayList<Integer> episodeIdList,
			ArrayList<Integer> sceneIdList, ArrayList<ReplyLength> replyLengths,
			ArrayList<HashMap<ScriptId, WordCount>> wordCounts) {
		this.personId = personId;
		this.seasonIdList = seasonIdList;
		this.episodeIdList = episodeIdList;
		this.sceneIdList = sceneIdList;
		this.replyLengths = replyLengths;
		this.wordCounts = wordCounts;
	}
	
	public Person(Integer id, String name){
		this.personId = new PersonId(id, name);
	}

	public PersonId getPersonId() {
		return personId;
	}

	public void setPersonId(Integer id, String name) {
		this.personId = new PersonId(id, name);
	}

	public ArrayList<Integer> getSeasonIdList() {
		return seasonIdList;
	}

	public void setSeasonIdList(ArrayList<Integer> seasonIdList) {
		this.seasonIdList = seasonIdList;
	}

	public ArrayList<Integer> getEpisodeIdList() {
		return episodeIdList;
	}

	public void setEpisodeIdList(ArrayList<Integer> episodeIdList) {
		this.episodeIdList = episodeIdList;
	}

	public ArrayList<Integer> getSceneIdList() {
		return sceneIdList;
	}

	public void setSceneIdList(ArrayList<Integer> sceneIdList) {
		this.sceneIdList = sceneIdList;
	}

	public ArrayList<ReplyLength> getReplyLengths() {
		return replyLengths;
	}

	public void setReplyLengths(ArrayList<ReplyLength> replyLengths) {
		this.replyLengths = replyLengths;
	}

	public ArrayList<HashMap<ScriptId, WordCount>> getWordCounts() {
		return wordCounts;
	}

	public void setWordCounts(ArrayList<HashMap<ScriptId, WordCount>> wordCounts) {
		this.wordCounts = wordCounts;
	}
	
}
