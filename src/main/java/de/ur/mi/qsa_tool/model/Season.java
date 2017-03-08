package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class Season {

	private ScriptId scriptId;
	private ArrayList<Integer> episodeIdList = new ArrayList<>();
	
	public Season(Integer seasonId, ArrayList<Integer> episodeIdList) {
		this.scriptId = new ScriptId(seasonId, 0, 0);
		this.episodeIdList = episodeIdList;
	}
	
	public Season(Integer seasonId) {
		this.scriptId = new ScriptId(seasonId, 0, 0);
	}

	public ScriptId getScriptId(){
		return scriptId;
	}
	
	public Integer getSeasonId() {
		return scriptId.getSeasonId();
	}

	public void setScriptId(Integer seasonId) {
		this.scriptId = new ScriptId(seasonId, 0, 0);
	}

	public ArrayList<Integer> getEpisodeList() {
		return episodeIdList;
	}

	public void setEpisodeList(ArrayList<Integer> episodeList) {
		this.episodeIdList = episodeList;
	}
	
}
