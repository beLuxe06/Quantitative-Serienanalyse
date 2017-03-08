package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class Episode {

	private ScriptId scriptId;
	private ArrayList<Integer> sceneIdList = new ArrayList<>();
	
	
	public Episode(Integer seasonId, Integer episodeId, ArrayList<Integer> sceneIdList) {
		this.scriptId = new ScriptId(seasonId, episodeId, 0);
		this.sceneIdList = sceneIdList;
	}
	
	public Episode(Integer seasonId, Integer episodeId) {
		this.scriptId = new ScriptId(seasonId, episodeId, 0);
	}

	public Integer getEpisodeId() {
		return scriptId.getEpisodeId();
	}

	public Integer getSeasonId() {
		return scriptId.getSeasonId();
	}

	public ScriptId getScriptId() {
		return scriptId;
	}


	public void setScriptId(Integer seasonId, Integer episodeId) {
		this.scriptId = new ScriptId(seasonId, episodeId, 0);
	}


	public ArrayList<Integer> getSceneIdList() {
		return sceneIdList;
	}


	public void setSceneIdList(ArrayList<Integer> sceneIdList) {
		this.sceneIdList = sceneIdList;
	}
	
	
	
}
