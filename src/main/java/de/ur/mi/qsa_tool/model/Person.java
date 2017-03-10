package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import de.ur.mi.qsa_tool.util.StringIntegerPairComparator;

public class Person implements Comparable<Person>{
	
	private PersonId personId;
	private ArrayList<Integer> seasonIdList = new ArrayList<>();
	private ArrayList<Integer> episodeIdList = new ArrayList<>();
	private ArrayList<Integer> sceneIdList = new ArrayList<>();
	private HashMap<Integer, Integer> replyLengths = new HashMap<>();
	private HashMap<String, Integer> wordCounts = new HashMap<>();
	private Integer wordNumbers = 0;
	private Integer speechNumbers = 0;
	
	public Person(PersonId personId, ArrayList<Integer> seasonIdList, ArrayList<Integer> episodeIdList,
			ArrayList<Integer> sceneIdList, HashMap<Integer, Integer> replyLengths,
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
	
	public HashMap<Integer, Integer> getReplyLengths() {
		return replyLengths;
	}

	public void setReplyLengths(HashMap<Integer, Integer> replyLengths) {
		this.replyLengths = replyLengths;
	}

	public HashMap<String, Integer> getWordCounts() {
		return wordCounts;
	}

	public void addLengthToReplyLengths(Integer length) {
		int count = 1;
		if(replyLengths.containsKey(length)){
			count = replyLengths.get(length)+1;
			replyLengths.remove(length);
			replyLengths.put(length, count);
		}
		else replyLengths.put(length, 1);
		
	}
	
	public void increaseWordCount(String word){
		int count = 1;
		if(wordCounts.containsKey(word)){
			count = wordCounts.get(word);
			wordCounts.remove(word);
			wordCounts.put(word, count+1);
		}
		else wordCounts.put(word, 1);
	}
	
	public void setWordCounts(HashMap<String, Integer> wordCounts) {
		this.wordCounts = wordCounts;
	}
	
	public String[] getMostImportantWordCounts2(int count) {
		String[] array = new String[count];
		Object[] a = wordCounts.entrySet().toArray();
		Arrays.sort(a, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>) o1).getValue();
			}
		});
		for(int i = 0; i<count; i++){
			Object e = a[i];
			String key = ((Map.Entry<String, Integer>) e).getKey();
			Integer value = ((Map.Entry<String, Integer>) e).getValue();
			String result = key + "(" + value.intValue() + ")";
			array[i] = result;
		}
		return array;
	}
	
	public String[] getMostImportantWordCounts(int count){
		ArrayList<StringIntegerPair> keyValueList = new ArrayList<>();
		ArrayList<String> keyList = new ArrayList<>();
		keyList.addAll(wordCounts.keySet());
		for(int i = 0; i<wordCounts.size(); i++){
			keyValueList.add(new StringIntegerPair(keyList.get(i), wordCounts.get(keyList.get(i))));
		}
		keyValueList.sort(new StringIntegerPairComparator());
		keyValueList.subList(count, keyValueList.size()).clear();
		String[] array = new String[count];
		for(int i = 0; i<count; i++){
			array[i] = keyValueList.get(i).getString() + "(" + keyValueList.get(i).getInteger() + ")";
		}
		return array;
	}
	
	public String[] getScenePresenceArray(Integer totalSceneCount){
		String[] scenePresenceArray = new String[totalSceneCount+1];
		scenePresenceArray[0] = personId.getName();
		for(int i = 0; i<totalSceneCount; i++){
			if(sceneIdList.contains(i+1)){
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
			if(episodeIdList.contains(i+1)){
				episodePresenceArray[i+1] = "1";
			}
			else episodePresenceArray[i+1] = "0";
		}
		return episodePresenceArray;
	}
	
	public String[] getSeasonPresenceArray(int totalSeasonCount) {
		String[] seasonPresenceArray = new String[totalSeasonCount+1];
		seasonPresenceArray[0] = personId.getName();
		for(int i = 0; i<totalSeasonCount; i++){
			if(seasonIdList.contains(i+1)){
				seasonPresenceArray[i+1] = "1";
			}
			else seasonPresenceArray[i+1] = "0";
		}
		return seasonPresenceArray;
	}

	@Override
	public int compareTo(Person person) {
		return person.speechNumbers < speechNumbers ? -1 : person.speechNumbers == speechNumbers ? 0 : 1;
	}
	
}
