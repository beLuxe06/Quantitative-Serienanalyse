package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Scene {

	private ScriptId scriptId;
	private String title;
	private ArrayList<Integer> lineNumbersList = new ArrayList<>();
	private ArrayList<Integer> personIdList = new ArrayList<>();
	private ArrayList<HashMap<String, Integer>> wordCounts = new ArrayList<>();
	private boolean isFlashback;
	private ScriptId flashbackReferenceScriptId;
	
	
	public Scene(ScriptId scriptId, String title, ArrayList<Integer> lineNumbersList, ArrayList<Integer> personIdList,
			ArrayList<HashMap<String, Integer>> wordCounts, boolean isFlashback, ScriptId flashbackReferenceScriptId) {
		this.scriptId = scriptId;
		this.title = title;
		this.lineNumbersList = lineNumbersList;
		this.personIdList = personIdList;
		this.wordCounts = wordCounts;
		this.isFlashback = isFlashback;
		this.flashbackReferenceScriptId = flashbackReferenceScriptId;
	}
	
	public Scene(Integer seasonId, Integer episodeId, Integer sceneId) {
		this.scriptId = new ScriptId(seasonId, episodeId, sceneId);
	}

	public ScriptId getScriptId() {
		return scriptId;
	}

	public void setScriptId(Integer seasonId, Integer episodeId, Integer sceneId) {
		this.scriptId = new ScriptId(seasonId, episodeId, sceneId);
	}

	public Integer getSeasonId(){
		return scriptId.getSeasonId();
	}
	
	public Integer getEpisodeId(){
		return scriptId.getEpisodeId();
	}
	
	public Integer getSceneId(){
		return scriptId.getSceneId();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Integer> getLineNumbersList() {
		return lineNumbersList;
	}

	public void setLineNumbersList(ArrayList<Integer> lineNumbersList) {
		this.lineNumbersList = lineNumbersList;
	}

	public ArrayList<Integer> getPersonIdList() {
		return personIdList;
	}

	public void setPersonIdList(ArrayList<Integer> personIdList) {
		this.personIdList = personIdList;
	}

	public ArrayList<HashMap<String, Integer>> getWordCounts() {
		return wordCounts;
	}

	public void setWordCounts(ArrayList<HashMap<String, Integer>> wordCounts) {
		this.wordCounts = wordCounts;
	}

	public boolean isFlashback() {
		return isFlashback;
	}

	public void setFlashback(boolean isFlashback) {
		this.isFlashback = isFlashback;
	}

	public ScriptId getFlashbackReferenceScriptId() {
		return flashbackReferenceScriptId;
	}

	public void setFlashbackReferenceScriptId(ScriptId flashbackReferenceScriptId) {
		this.flashbackReferenceScriptId = flashbackReferenceScriptId;
	}
	
	
	
	
}
