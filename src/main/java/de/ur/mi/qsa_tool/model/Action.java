package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class Action {

	private Integer id;
	private String sentence;
	private ArrayList <Integer> personRefs = new ArrayList <> ();
	private ArrayList <String> words = new ArrayList <> ();
	private Integer personId;
	
	public Action(Integer id, String sentence, ArrayList<Integer> personRefs, ArrayList<String> words,
			Integer personId) {
		this.id = id;
		this.sentence = sentence;
		this.personRefs = personRefs;
		this.words = words;
		this.personId = personId;
	}
	
	public Action(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public ArrayList<Integer> getPersonRefs() {
		return personRefs;
	}
	public void setPersonRefs(ArrayList<Integer> personRefs) {
		this.personRefs = personRefs;
	}
	public ArrayList<String> getWords() {
		return words;
	}
	public void setWords(ArrayList<String> words) {
		this.words = words;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
}
