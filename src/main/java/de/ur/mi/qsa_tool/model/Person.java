package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	
	private PersonId personId;
	private ArrayList<Integer> seasonIdList = new ArrayList<>();
	private ArrayList<Integer> episodeIdList = new ArrayList<>();
	private ArrayList<Integer> sceneIdList = new ArrayList<>();
	private ArrayList<ReplyLength> replyLengths = new ArrayList<>();
	private HashMap<String, Integer> wordCounts = new HashMap<>();
	private Integer wordNumbers = 0;
	private Integer speechNumbers = 0;
	
	public Person(PersonId personId, ArrayList<Integer> seasonIdList, ArrayList<Integer> episodeIdList,
			ArrayList<Integer> sceneIdList, ArrayList<ReplyLength> replyLengths,
			HashMap<String, Integer> wordCounts, Integer wordNumbers, Integer speechNumbers) {
		this.personId = personId;
		this.seasonIdList = seasonIdList;
		this.episodeIdList = episodeIdList;
		this.sceneIdList = sceneIdList;
		this.replyLengths = replyLengths;
		this.wordCounts = wordCounts;
		this.wordNumbers = wordNumbers;
		this.speechNumbers = speechNumbers;
	}
	
	public Integer getWordNumbers() {
		return wordNumbers;
	}

	public void setWordNumbers(Integer wordNumbers) {
		this.wordNumbers = wordNumbers;
	}

	public Integer getSpeechNumbers() {
		return speechNumbers;
	}

	public void setSpeechNumbers(Integer speechNumbers) {
		this.speechNumbers = speechNumbers;
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
	
	public ReplyLength getReplyLength(ScriptId scriptId) {
		for(ReplyLength replyLength : replyLengths){
			if(replyLength.getScriptId().equals(scriptId))
				return replyLength;
		}
		return null;
	}
	
	public ArrayList<ReplyLength> getReplyLengths() {
		return replyLengths;
	}

	public void setReplyLengths(ArrayList<ReplyLength> replyLengths) {
		this.replyLengths = replyLengths;
	}

	public HashMap<String, Integer> getWordCounts() {
		return wordCounts;
	}

	public void increaseWordCount(String word){
		int count = 1;
		if(wordCounts.containsKey(word)){
			count = wordCounts.get(word);
			count++;
			wordCounts.remove(word);
			wordCounts.put(word, count);
		}
		else wordCounts.put(word, count);
	}
	
	public void setWordCounts(HashMap<String, Integer> wordCounts) {
		this.wordCounts = wordCounts;
	}
	
	public String[] getScenePresenceArray(Integer totalSceneCount){
		String[] scenePresenceArray = new String[totalSceneCount+1];
		scenePresenceArray[0] = personId.getName();
		for(int i = 0; i<totalSceneCount; i++){
			if(sceneIdList.contains(i)){
				scenePresenceArray[i+1] = "1";
			}
			else scenePresenceArray[i+1] = "0";
		}
		return scenePresenceArray;
	}

	public String[] getEpisodePresenceArray(int totalEpisodeCount) {
		String[] episodePresenceArray = new String[totalEpisodeCount+1];
		episodePresenceArray[0] = personId.getName();
		for(int i = 0; i<totalEpisodeCount; i++){
			if(episodeIdList.contains(i)){
				episodePresenceArray[i+1] = "1";
			}
			else episodePresenceArray[i+1] = "0";
		}
		return episodePresenceArray;
	}
	
	public String[] getSeasonPresenceArray(int totalSeasonCount) {
		String[] seasonPresenceArray = new String[totalSeasonCount+1];
		seasonPresenceArray[0] = personId.getName();
		for(int i = 1; i<totalSeasonCount; i++){
			if(seasonIdList.contains(i)){
				seasonPresenceArray[i] = "1";
			}
			else seasonPresenceArray[i] = "0";
		}
		return seasonPresenceArray;
	}
	
}
