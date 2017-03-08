package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class Episode {

	private ScriptId scriptId;
	private ArrayList<Scene> sceneList = new ArrayList<>();
	
	
	public Episode(Integer seasonId, Integer episodeId, ArrayList<Scene> sceneList) {
		this.scriptId = new ScriptId(seasonId, episodeId, 0);
		this.sceneList = sceneList;
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


	public ArrayList<Scene> getSceneList() {
		return sceneList;
	}


	public void setSceneList(ArrayList<Scene> sceneList) {
		this.sceneList = sceneList;
	}
	
	
	
}
