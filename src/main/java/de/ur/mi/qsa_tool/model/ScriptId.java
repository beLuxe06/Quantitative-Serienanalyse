package de.ur.mi.qsa_tool.model;

public class ScriptId {
	
	private Integer seasonId;
	private Integer episodeId;
	private Integer sceneId;
	
	public ScriptId(Integer seasonId, Integer episodeId, Integer sceneId) {
		this.seasonId = seasonId;
		this.episodeId = episodeId;
		this.sceneId = sceneId;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	
	

}
