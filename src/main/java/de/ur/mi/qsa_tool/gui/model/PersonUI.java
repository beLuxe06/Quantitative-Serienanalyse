package de.ur.mi.qsa_tool.gui.model;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.model.Person;

public class PersonUI {

	private String name;
	private String seasonsPresence;
	private String seasonsPresenceSize;
	private String appereanceSeasonsShare;
	private String episodesPresence;
	private String episodesPresenceSize;
	private String appereanceEpisodesShare;
	private String scenesPresence;
	private String scenesPresenceSize;
	private String appereanceScenesShare;
	private Integer numberOfSeasons;
	private Integer numberOfEpisodes;
	private Integer numberOfScenes;
	private String speechNumbers;
	private String wordNumbers;
	private String sentences;
	
	private final int NUM_OF_MOST_ITEMS_PER_LINE = 12;
	private final String NEW_LINE = "\n";
	private final String SEPERATOR = "; ";
	
	public PersonUI(Person person, Integer numberOfSeasons, Integer numberOfEpisodes, Integer numberOfScenes){
		this.name = person.getPersonId().getName();
		this.numberOfEpisodes = numberOfEpisodes;
		this.numberOfScenes = numberOfScenes;
		this.numberOfSeasons = numberOfSeasons;
		this.seasonsPresence = getArrayListAsSingleString(person.getSeasonIdList());
		this.episodesPresence = getArrayListAsSingleString(person.getEpisodeIdList());
		this.scenesPresence = getArrayListAsSingleString(person.getSceneIdList());
		this.seasonsPresenceSize = "" + person.getSeasonIdList().size();
		this.episodesPresenceSize = "" + person.getEpisodeIdList().size();
		this.scenesPresenceSize = "" + person.getSceneIdList().size();
		this.appereanceSeasonsShare = getShareOfListSize(person.getSeasonIdList().size(), numberOfSeasons);
		this.appereanceEpisodesShare = getShareOfListSize(person.getEpisodeIdList().size(), numberOfEpisodes);
		this.appereanceScenesShare = getShareOfListSize(person.getSceneIdList().size(), numberOfScenes);
		this.speechNumbers = person.getSpeechNumbers().toString();
		this.wordNumbers = person.getWordNumbers().toString();
		this.sentences = person.getSentences().toString();
	}
	
	public String getSentences() {
		return sentences;
	}

	public void setSentences(String sentences) {
		this.sentences = sentences;
	}

	public String getSeasonsPresenceSize() {
		return seasonsPresenceSize;
	}

	public void setSeasonsPresenceSize(String seasonsPresenceSize) {
		this.seasonsPresenceSize = seasonsPresenceSize;
	}

	public String getEpisodesPresenceSize() {
		return episodesPresenceSize;
	}

	public void setEpisodesPresenceSize(String episodesPresenceSize) {
		this.episodesPresenceSize = episodesPresenceSize;
	}

	public String getScenesPresenceSize() {
		return scenesPresenceSize;
	}

	public void setScenesPresenceSize(String scenesPresenceSize) {
		this.scenesPresenceSize = scenesPresenceSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeasonsPresence() {
		return seasonsPresence;
	}

	public void setSeasonsPresence(String seasonsPresence) {
		this.seasonsPresence = seasonsPresence;
	}

	public String getAppereanceSeasonsShare() {
		return appereanceSeasonsShare;
	}

	public void setAppereanceSeasonsShare(String appereanceSeasonsShare) {
		this.appereanceSeasonsShare = appereanceSeasonsShare;
	}

	public String getEpisodesPresence() {
		return episodesPresence;
	}

	public void setEpisodesPresence(String episodesPresence) {
		this.episodesPresence = episodesPresence;
	}

	public String getAppereanceEpisodesShare() {
		return appereanceEpisodesShare;
	}

	public void setAppereanceEpisodesShare(String appereanceEpisodesShare) {
		this.appereanceEpisodesShare = appereanceEpisodesShare;
	}

	public String getScenesPresence() {
		return scenesPresence;
	}

	public void setScenesPresence(String scenesPresence) {
		this.scenesPresence = scenesPresence;
	}

	public String getAppereanceScenesShare() {
		return appereanceScenesShare;
	}

	public void setAppereanceScenesShare(String appereanceScenesShare) {
		this.appereanceScenesShare = appereanceScenesShare;
	}

	public String getSpeechNumbers() {
		return speechNumbers;
	}

	public void setSpeechNumbers(String speechNumbers) {
		this.speechNumbers = speechNumbers;
	}

	public String getWordNumbers() {
		return wordNumbers;
	}

	public void setWordNumbers(String wordNumbers) {
		this.wordNumbers = wordNumbers;
	}

	private String getShareOfListSize(int shareSize, Integer listSize) {
		float share = (100 * shareSize) / listSize;
	    return String.format("%.0f%%",share);
	}

	private String getArrayListAsSingleString(ArrayList<Integer> list){
		String singleString = "";
		for(int i = 0; i < list.size(); i++){
			singleString = singleString.concat(list.get(i).toString() + SEPERATOR);
			if((i%NUM_OF_MOST_ITEMS_PER_LINE)==0 && i != 0){
				singleString = singleString.concat(NEW_LINE);
			}
		}
		removeLastNeedlessCharacters(singleString);
		return singleString;
	}
	
	private void removeLastNeedlessCharacters(String singleString) {
		if(singleString.endsWith(NEW_LINE)){
			singleString = singleString.substring(0, singleString.lastIndexOf(NEW_LINE));
		}
		if(singleString.endsWith(SEPERATOR)){
			singleString = singleString.substring(0, singleString.lastIndexOf(SEPERATOR));
		}
		
	}
}
