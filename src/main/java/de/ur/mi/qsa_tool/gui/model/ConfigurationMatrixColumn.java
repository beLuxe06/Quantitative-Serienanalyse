package de.ur.mi.qsa_tool.gui.model;

import java.util.Comparator;

public class ConfigurationMatrixColumn implements Comparable<String>{

	private String[] personPresence;

	public ConfigurationMatrixColumn(String[] personPresence) {
		this.personPresence = personPresence;
	}

	public String getSceneId() {
		return personPresence[0];
	}

	public void setSceneId(String sceneId) {
		this.personPresence[0] = sceneId;
	}

	public String getPersonPresenceByScene(Integer sceneId){
		return personPresence[sceneId+1];
	}
	
	public String[] getColumnContent() {
		return personPresence;
	}

	public void setColumnContent(String[] personPresence) {
		this.personPresence = personPresence;
	}

	@Override
	public int compareTo(String scene2Id) {
		return Comparator.<String>naturalOrder().compare(personPresence[0], scene2Id);
	}
}