package de.ur.mi.qsa_tool.gui.model;

public class ConfigurationMatrixRow implements Comparable<String>{

	private String personName;
	private String[] scenePresence;
	
	public ConfigurationMatrixRow(String personName, String[] scenePresence) {
		this.personName = personName;
		this.scenePresence = scenePresence;
	}
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String[] getScenePresence() {
		return scenePresence;
	}

	public void setScenePresence(String[] scenePresence) {
		this.scenePresence = scenePresence;
	}


	@Override
	public int compareTo(String arg0) {
		return getPersonName().compareTo(arg0);
	}

}
