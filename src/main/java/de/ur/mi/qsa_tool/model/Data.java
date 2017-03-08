package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Data extends ArrayList<ArrayList<HashMap<String, String>>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4069220786905564910L;
	private ArrayList<ArrayList<HashMap<String, String>>> data;  
	
	public Data(ArrayList<ArrayList<HashMap<String, String>>> data){
		this.data = data;
	}

	public ArrayList<ArrayList<HashMap<String, String>>> getData() {
		return data;
	}
	
	public ArrayList<HashMap<String, String>> getMetaData(){
		return data.get(0);
	}
	
	public DiagramRawData getConfigurationMatrixData(){
		return (DiagramRawData) data.get(1);
	}
	
	public DiagramRawData getWordCountsData(){
		return (DiagramRawData) data.get(2);
	}
	
	public DiagramRawData getPersonConstellationsData(){
		return (DiagramRawData) data.get(3);
	}
	
	public DiagramRawData getTimeLineData(){
		return (DiagramRawData) data.get(4);
	}

	@Override
	public String toString() {
		String toString = "Data: {} + size {}" ;
		return toString + super.toString() + data.size();
	}
	
	

}
