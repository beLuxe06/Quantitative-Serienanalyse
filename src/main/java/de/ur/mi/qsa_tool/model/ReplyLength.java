package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;

public class ReplyLength {
	
	private ScriptId scriptId;
	private ArrayList<Integer> lengths = new ArrayList<>();
	
	public ReplyLength(ScriptId scriptId, ArrayList<Integer> lengths) {
		this.scriptId = scriptId;
		this.lengths = lengths;
	}
	
	public ReplyLength(ScriptId scriptId, Integer lengths) {
		this.scriptId = scriptId;
		this.lengths.add(lengths);
	}

	public ScriptId getScriptId() {
		return scriptId;
	}

	public void setScriptId(ScriptId scriptId) {
		this.scriptId = scriptId;
	}

	public ArrayList<Integer> getLengths() {
		return lengths;
	}

	public void setLengths(ArrayList<Integer> lengths) {
		this.lengths = lengths;
	}
	
	

}
